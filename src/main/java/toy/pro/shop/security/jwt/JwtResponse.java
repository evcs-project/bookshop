package toy.pro.shop.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import toy.pro.shop.web.member.domain.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JwtResponse {
    private String email;
    private String token;
    Set<String> authorityList;

    public JwtResponse(String email, String token, Set<String> authorityList)
    {
        this.email = email;
        this.token = token;
        this.authorityList = authorityList;
    }

    public JwtResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
