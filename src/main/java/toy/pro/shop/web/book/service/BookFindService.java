package toy.pro.shop.web.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.book.dto.BookSearchDto;
import toy.pro.shop.web.book.dto.BookSearchRequestDto;

@RequiredArgsConstructor
@Service
public class BookFindService {

    private final BookRepository bookRepository;

    public Page<BookSearchDto> search(BookSearchRequestDto requestDto)
    {
        return bookRepository.search(requestDto);
    }
}
