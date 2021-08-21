package toy.pro.shop.web.book.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchRequestDto {

    private Integer size;
    private Integer page;
    private String bookNm;
    private String publisher;
    private String isbn;
    private String writer;
    private Long categoryId;
}
