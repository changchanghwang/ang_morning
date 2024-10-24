package chang.ang_morning_server.common.security

import chang.ang_morning_server.services.members.domain.Member
import chang.ang_morning_server.services.members.domain.MemberRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(stringMemberId: String): UserDetails {
        val memberId = UUID.fromString(stringMemberId)
        val member =
            memberRepository.findById(memberId)
                ?: throw UsernameNotFoundException("Member not found with id: $memberId")

        return UserPrincipal(member)
    }
}

class UserPrincipal(
    private val member: Member
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getUsername(): String = member.id.toString()

    override fun getPassword(): String = member.password
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}