package chang.ang_morning_server.services.auth.presentation

import chang.ang_morning_server.services.auth.application.AuthService
import chang.ang_morning_server.services.auth.command.OAuthCommand
import chang.ang_morning_server.services.auth.command.SignInCommand
import chang.ang_morning_server.services.auth.command.TokenRefreshCommand
import chang.ang_morning_server.services.auth.command.TokenResponse
import chang.ang_morning_server.services.members.domain.ProviderType
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/sign-in")
    fun signIn(
        @Valid @RequestBody command: SignInCommand,
        @RequestHeader("User-Agent") clientInfo: String
    ): ResponseEntity<TokenResponse> {
        val tokenResponse = authService.signIn(command, clientInfo)
        return ResponseEntity.ok(tokenResponse)
    }


    @PostMapping("/sign-in/{provider}")
    fun oauth(
        @PathVariable provider: ProviderType,
        @Valid @RequestBody command: OAuthCommand,
        @RequestHeader("User-Agent") clientInfo: String
    ): ResponseEntity<TokenResponse> {
        val tokenResponse = authService.oAuth(provider, command, clientInfo)
        return ResponseEntity.ok(tokenResponse)
    }


    @PostMapping("/refresh")
    fun refresh(
        @Valid @RequestBody command: TokenRefreshCommand
    ): ResponseEntity<TokenResponse> {
        val tokenResponse = authService.refresh(command)
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping("/sign-out")
    fun signOut(
        @RequestHeader("Authorization") accessToken: String?,
        @RequestHeader("User-Agent") clientInfo: String
    ): ResponseEntity<Unit> {
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            val token = accessToken.substring(7)
            authService.signOut(token, clientInfo)
        }
        return ResponseEntity.ok().build()
    }
}