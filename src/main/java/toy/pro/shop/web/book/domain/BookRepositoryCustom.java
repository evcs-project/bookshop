package toy.pro.shop.web.book.domain;


import org.springframework.data.domain.Page;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;


public interface BookRepositoryCustom {

    Page<BookSearchResponseDto> search(BookSearchRequestDto requestDto);

}
