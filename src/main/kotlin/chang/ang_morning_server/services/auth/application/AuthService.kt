package chang.ang_morning_server.services.auth.application

import chang.ang_morning_server.common.exception.Unauthorized
import chang.ang_morning_server.common.oauth.OauthClientFactory
import chang.ang_morning_server.common.security.JwtTokenService
import chang.ang_morning_server.services.auth.command.OAuthCommand
import chang.ang_morning_server.services.auth.command.SignInCommand
import chang.ang_morning_server.services.auth.command.TokenRefreshCommand
import chang.ang_morning_server.services.auth.command.TokenResponse
import chang.ang_morning_server.services.auth.domain.RefreshToken
import chang.ang_morning_server.services.auth.domain.RefreshTokenRepository
import chang.ang_morning_server.services.members.domain.Member
import chang.ang_morning_server.services.members.domain.MemberRepository
import chang.ang_morning_server.services.members.domain.ProviderType
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtTokenService: JwtTokenService,
    private val passwordEncoder: PasswordEncoder,
    private val oauthClientFactory: OauthClientFactory
) {
    @Transactional
    fun signIn(command: SignInCommand, clientInfo: String?): TokenResponse {
        val member =
            memberRepository.findByEmail(command.email) ?: throw Unauthorized(
                "Invalid email",
                "이메일 또는 비밀번호가 일치하지 않습니다."
            )

        if (!this.passwordEncoder.matches(command.password, member.password)) {
            throw Unauthorized("Invalid password", "이메일 또는 비밀번호가 일치하지 않습니다.")
        }

        val accessToken = this.jwtTokenService.createAccessToken(member.id)
        val refreshToken = this.jwtTokenService.createRefreshToken()
        member.signIn(ProviderType.LOCAL)

        this.memberRepository.save(member)
        this.refreshTokenRepository.save(
            RefreshToken(
                token = refreshToken,
                member = member,
                clientInfo = clientInfo ?: "unknown",
            )
        )

        return TokenResponse(accessToken, refreshToken)
    }

    @Transactional
    fun oAuth(command: OAuthCommand, clientInfo: String?): TokenResponse {
        val client = this.oauthClientFactory.getClient(command.provider)

        val oAuthToken = client.getToken(command.code)
        val oAuthUserInfo = client.getUserInfo(oAuthToken.accessToken)

        val member = memberRepository.findByEmail(oAuthUserInfo.email)
            ?.also { it.addProvider(command.provider) }
            ?: Member.of(oAuthUserInfo.email, "passwprd", oAuthUserInfo.nickname, ProviderType.KAKAO)

        val accessToken = this.jwtTokenService.createAccessToken(member.id)
        val refreshToken = this.jwtTokenService.createRefreshToken()

        member.signIn(ProviderType.KAKAO)
        this.memberRepository.save(member)
        this.refreshTokenRepository.save(
            RefreshToken(
                token = refreshToken,
                member = member,
                clientInfo = clientInfo ?: "unknown",
            )
        )

        return TokenResponse(accessToken, refreshToken)
    }

    @Transactional
    fun refresh(command: TokenRefreshCommand): TokenResponse {
        if (!this.jwtTokenService.validateToken(command.refreshToken, isAccessToken = false)) {
            throw Unauthorized("Invalid refresh token", "유효하지 않은 토큰입니다.")
        }

        val oldRefreshToken = refreshTokenRepository.findByToken(command.refreshToken)
            ?: throw Unauthorized("Refresh token not found", "토큰을 찾을 수 없습니다.")

        val member = oldRefreshToken.member
        val accessToken = this.jwtTokenService.createAccessToken(member.id)
        val newRefreshToken = this.jwtTokenService.createRefreshToken()

        oldRefreshToken.rotate(newRefreshToken)
        this.refreshTokenRepository.save(oldRefreshToken)

        return TokenResponse(accessToken, newRefreshToken)
    }

    @Transactional
    fun signOut(accessToken: String, clientInfo: String) {
        val memberId = jwtTokenService.extractMemberIdFromToken(accessToken)
        this.refreshTokenRepository.deleteByMemberIdAndClientInfo(memberId, clientInfo)
    }
}