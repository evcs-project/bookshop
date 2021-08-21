package toy.pro.shop.web.book.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchResponseDto {

    protected Long bookId;
    protected String bookNm;
    protected String writer;
    protected String publisher;
    protected String isbn;
    protected Long categoryId;
    protected String categoryName;
    protected String thumbnailUrl;
    protected Integer price;

    public BookSearchResponseDto(
            Long bookId
            , String bookNm
            , String writer
            , String publisher
            , String isbn
            , String thumbnailUrl
            , Long categoryId
            , String categoryName
            , Integer price
    )
    {
        this.bookId = bookId;
        this.bookNm = bookNm;
        this.writer = writer;
        this.publisher = publisher;
        this.isbn = isbn;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
    }
}
