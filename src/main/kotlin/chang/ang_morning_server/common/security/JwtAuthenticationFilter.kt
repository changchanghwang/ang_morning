package chang.ang_morning_server.common.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtTokenService: JwtTokenService,
    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = extractJwtFromRequest(request)

            if (jwt != null && jwtTokenService.validateToken(jwt, isAccessToken = true)) {
                val memberId = jwtTokenService.extractMemberIdFromToken(jwt)
                val userDetails = userDetailsService.loadUserByUsername(memberId.toString())

                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("JWT 토큰 검증 실패", e)
        }

        filterChain.doFilter(request, response)
    }

    private fun extractJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }
}
