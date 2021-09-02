package toy.pro.shop.web.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookId;
import toy.pro.shop.web.cart.domain.Cart;
import toy.pro.shop.web.review.domain.Review;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    public Member(String name) {
        this.name = name;
    }

    @ElementCollection
    @CollectionTable(name = "member_cart_book", joinColumns = @JoinColumn(name = "member_id"))
    private Set<BookId> bookIds;

    @OneToMany(mappedBy = "member")
    List<Review> reviewList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "members_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public static Member newMember(String password, String email, String name, List<Role> roles)
    {
        Member member = new Member(password, email, name);
        member.roles = roles;
        return member;
    }

    private Member(String password, String email, String name)
    {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public void changePassword(String oldPassword, String newPassword)
    {
        if (oldPassword.matches(newPassword))
            throw new IllegalArgumentException("새로운 비밀번호가 아닙니다.");

        setPassword(newPassword);
    }

    public void setPassword(String newPassword)
    {
        if (newPassword.isEmpty())
            throw new IllegalArgumentException("새로운 비밀번호가 없습니다.");

        this.password = newPassword;
    }
}
