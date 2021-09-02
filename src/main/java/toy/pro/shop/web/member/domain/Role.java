package toy.pro.shop.web.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(of = "name")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleName name;

    private Role(RoleName roleName)
    {
        this.name = roleName;
    }
}
