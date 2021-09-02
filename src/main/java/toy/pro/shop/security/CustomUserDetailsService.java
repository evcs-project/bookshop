package toy.pro.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    public CustomUserDetailsService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email)
    {
        return memberRepository.findByEmail(email)
                .map(this::createUser)
                .orElseThrow(() -> new BadCredentialsException(ErrorCode.USER_NOT_FOUND.getName()));
    }

    private User createUser(Member member)
    {
        return new SecurityUser(member);
    }
}