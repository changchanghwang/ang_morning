package chang.ang_morning_server.services.members.domain.services

import at.favre.lib.crypto.bcrypt.BCrypt
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PasswordService(
    @Value("\${hash.cost}") private val cost: String
) {
    fun hash(password: String): String {
        return BCrypt.withDefaults().hashToString(cost.toInt(), password.toCharArray())
    }
}