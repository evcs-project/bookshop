package toy.pro.shop.security.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import toy.pro.shop.web.member.domain.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String email;
    private String token;
    private Long memberId;
    Set<String> authorityList;

    public JwtResponse(String email, String token, Set<String> authorityList, Long memberId)
    {
        this.email = email;
        this.token = token;
        this.authorityList = authorityList;
        this.memberId = memberId;
    }

    public JwtResponse(String email, Set<String> authorityList, Long memberId)
    {
        this.email = email;
        this.authorityList = authorityList;
        this.memberId = memberId;
    }

}
