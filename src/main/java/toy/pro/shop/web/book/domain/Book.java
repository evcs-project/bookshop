package toy.pro.shop.web.book.domain;

import lombok.Getter;
import lombok.ToString;
import toy.pro.shop.common.Money;

import javax.persistence.*;

@Getter
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
