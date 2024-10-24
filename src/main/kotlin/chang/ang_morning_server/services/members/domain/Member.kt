package chang.ang_morning_server.services.members.domain

import chang.ang_morning_server.common.ddd.AggregateRoot
import com.fasterxml.uuid.Generators
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "member")
class Member(
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val nickname: String,
    @Id val id: UUID
) : AggregateRoot() {
    companion object {
        fun of(email: String, hashedPassword: String, nickname: String): Member {
            return Member(email, hashedPassword, nickname, Generators.timeBasedEpochGenerator().generate())
        }
    }
}