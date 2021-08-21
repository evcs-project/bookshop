package toy.pro.shop.web.book.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.pro.shop.web.book.dto.BookSearchDto;
import toy.pro.shop.web.book.dto.BookSearchRequestDto;

public interface BookRepositoryCustom {
    Page<Book> searchByBookNm(String bookNm, Pageable pageable);
    Page<Book> searchByWriter(String writer, Pageable pageable);
    Page<BookSearchDto> search(BookSearchRequestDto requestDto);

}
