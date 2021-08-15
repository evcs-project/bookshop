package toy.pro.shop.web.book.domain;

import lombok.Getter;
import lombok.ToString;

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

    private String price;

    @Column(name = "thumbnail_url")
    private String thumbNail;
    private String description;
    private String section;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Category category;
}
