package toy.pro.shop.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import toy.pro.shop.web.member.domain.Member;



public class SecurityUser extends User {
    public SecurityUser(Member member) {
        super(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRoles().toString()));
    }
}
