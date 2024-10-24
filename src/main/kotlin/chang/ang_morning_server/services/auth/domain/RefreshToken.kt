package chang.ang_morning_server.services.auth.domain

import chang.ang_morning_server.services.members.domain.Member
import jakarta.persistence.*

@Entity(name = "refreshToken")
class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var token: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    val member: Member,

    @Column(nullable = false)
    val clientInfo: String
) {
    fun rotate(newToken: String) {
        this.token = newToken
    }
}
