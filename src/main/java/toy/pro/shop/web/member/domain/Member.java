package toy.pro.shop.web.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookId;
import toy.pro.shop.web.cart.domain.Cart;
import toy.pro.shop.web.review.domain.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    @ElementCollection
    @CollectionTable(name = "member_cart_book", joinColumns = @JoinColumn(name = "member_id"))
    private Set<BookId> bookIds;

    @OneToMany(mappedBy = "member")
    List<Review> reviewList = new ArrayList<>();

}
