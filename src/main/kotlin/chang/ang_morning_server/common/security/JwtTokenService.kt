package chang.ang_morning_server.common.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import java.security.SignatureException
import java.util.*

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val accessTokenSecret: String,
    val refreshTokenSecret: String,
    val accessTokenExpiration: Long = 24 * 60 * 60 * 1000,  // 24시간
    val refreshTokenExpiration: Long = 30L * 24 * 60 * 60 * 1000  // 30일
)

@Service
class JwtTokenService(
    private val jwtProperties: JwtProperties,
) {
    fun createAccessToken(memberId: UUID): String {
        val now = Date()
        val validity = Date(now.time + jwtProperties.accessTokenExpiration)

        val key = Keys.hmacShaKeyFor(jwtProperties.accessTokenSecret.toByteArray())
        return Jwts.builder()
            .setSubject(memberId.toString())
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun createRefreshToken(): String {
        val now = Date()
        val validity = Date(now.time + jwtProperties.refreshTokenExpiration)

        val key = Keys.hmacShaKeyFor(jwtProperties.refreshTokenSecret.toByteArray())
        return Jwts.builder()
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun validateToken(token: String, isAccessToken: Boolean): Boolean {
        return try {
            val secret = if (isAccessToken)
                jwtProperties.accessTokenSecret
            else
                jwtProperties.refreshTokenSecret

            val key = Keys.hmacShaKeyFor(secret.toByteArray())

            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException,
                is UnsupportedJwtException,
                is MalformedJwtException,
                is SignatureException,
                is IllegalArgumentException -> false

                else -> throw e
            }
        }
    }

    fun extractMemberIdFromToken(token: String): UUID {
        val key = Keys.hmacShaKeyFor(jwtProperties.accessTokenSecret.toByteArray())
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        return UUID.fromString(claims.subject)
    }
}