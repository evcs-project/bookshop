package toy.pro.shop.web.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.book.dto.response.BookInfoResponseDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;

@RequiredArgsConstructor
@Service
public class BookFindService {

    private final BookRepository bookRepository;

    public Page<BookSearchResponseDto> search(BookSearchRequestDto requestDto)
    {
        return bookRepository.search(requestDto);
    }

    public BookInfoResponseDto searchBookById(Long bookId)
    {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("없는 책 ID"));
        return BookInfoResponseDto.toResponseDto(book);
    }
}
