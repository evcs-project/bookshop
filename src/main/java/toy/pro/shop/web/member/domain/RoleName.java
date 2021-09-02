package toy.pro.shop.web.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoleName {
    ADMIN("ADMIN"), USER("USER");
    private String nm;
}
