package toy.pro.shop.web.book.dto.response;

import lombok.*;
import toy.pro.shop.web.book.domain.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoResponseDto extends BookSearchResponseDto {

    private String description;
    private String section;

    @Builder
    private BookInfoResponseDto(
            Long bookId
            , String bookNm
            , String writer
            , String publisher
            , String isbn
            , String thumbnailUrl
            , Long categoryId
            , String categoryName
            , Integer price
            , String description
            , String section
    )
    {
        this.bookId = bookId;
        this.bookNm = bookNm;
        this.writer = writer;
        this.publisher = publisher;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.thumbnailUrl = thumbnailUrl;
        this.categoryName = categoryName;
        this.price = price;
        this.description = description;
        this.section = section;
    }

    public static BookInfoResponseDto toResponseDto(Book book)
    {
        return BookInfoResponseDto.builder()
                .bookId(book.getBookId())
                .bookNm(book.getBookNm())
                .writer(book.getWriter())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .thumbnailUrl(book.getThumbNail())
                .categoryId(book.getCategory().getCategoryId())
                .categoryName(book.getCategory().getCategoryName())
                .price(book.getPrice().getValue())
                .description(book.getDescription())
                .section(book.getSection())
                .build();
    }
}
