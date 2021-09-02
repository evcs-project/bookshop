package toy.pro.shop.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import toy.pro.shop.web.member.domain.Member;


@Setter
@Getter
public class SecurityUser extends User {
    private Long memberId;

    public SecurityUser(Member member) {
        super(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRoles().toString()));
        this.memberId = member.getId();
    }
}
