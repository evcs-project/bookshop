package toy.pro.shop.web.book.domain;

import lombok.Getter;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.review.domain.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(unique = true)
    private String isbn;
    private String bookNm;
    private String writer;

    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "price"))})
    @Embedded
    private Money price;

    @Column(name = "thumbnail_url")
    private String thumbNail;
    private String description;
    private String section;

    @Column(name = "publisher")
    private String publisher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    List<Review> reviewList = new ArrayList<>();


}
