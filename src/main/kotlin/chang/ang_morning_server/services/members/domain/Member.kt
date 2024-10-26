package chang.ang_morning_server.services.members.domain

import chang.ang_morning_server.common.ddd.AggregateRoot
import com.fasterxml.uuid.Generators
import jakarta.persistence.Column
import jakarta.persistence.Convert
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

    @Convert(converter = ProviderTypeListConverter::class)
    @Column(nullable = false)
    var providers: List<ProviderType> = listOf(),

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
                providers = listOf(provider),
                null,
                Generators.timeBasedEpochGenerator().generate()
            )
        }
    }

    fun signIn(provider: ProviderType) {
        if (!this.providers.contains(provider)) {
            this.providers += provider
        }
        this.lastProviderType = provider
    }
}