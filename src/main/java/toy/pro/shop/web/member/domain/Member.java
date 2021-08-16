package toy.pro.shop.web.member.domain;

import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookId;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "member_cart_book", joinColumns = @JoinColumn(name = "member_id"))
    private Set<BookId> bookIds;
}
