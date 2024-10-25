package chang.ang_morning_server.services.members.domain

import chang.ang_morning_server.common.ddd.AggregateRoot
import com.fasterxml.uuid.Generators
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

enum class ProviderType {
    NAVER,
    KAKAO,
    GOOGLE,
    LOCAL
}

@Entity(name = "member")
class Member(
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val nickname: String,
    @Column(nullable = false)
    val provider: ProviderType,
    @Column(nullable = true)
    var lastProviderType: ProviderType?,
    @Id val id: UUID
) : AggregateRoot() {
    companion object {
        fun of(
            email: String,
            hashedPassword: String,
            nickname: String,
            provider: ProviderType,
        ): Member {
            return Member(
                email,
                hashedPassword,
                nickname,
                provider,
                null,
                Generators.timeBasedEpochGenerator().generate()
            )
        }
    }

    fun addProvider(provider: ProviderType) {

    }

    fun signIn(provider: ProviderType) {
        this.lastProviderType = provider
    }
}