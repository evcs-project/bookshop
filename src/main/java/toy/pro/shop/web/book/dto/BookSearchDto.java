package toy.pro.shop.web.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchDto {

    private Long bookId;
    private String bookNm;
    private String writer;
    private String publisher;
    private String isbn;
    private String section;

    public BookSearchDto(
            Long bookId
            , String bookNm
            , String writer
            , String publisher
            , String isbn
            , String section)
    {
        this.bookId = bookId;
        this.bookNm = bookNm;
        this.writer = writer;
        this.publisher = publisher;
        this.isbn = isbn;
        this.section = section;
    }
}
