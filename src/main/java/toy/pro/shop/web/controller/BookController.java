package toy.pro.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.book.dto.response.BookInfoResponseDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;
import toy.pro.shop.web.book.service.BookFindService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookFindService bookFindService;

    @GetMapping("/search")
    public Page<BookSearchResponseDto> searchBooks(BookSearchRequestDto requestDto)
    {
        return bookFindService.search(requestDto);
    }

    @GetMapping("/{bookId}")
    public BookInfoResponseDto searchBook(@PathVariable(value = "bookId") Long bookId)
    {
        return bookFindService.searchBookById(bookId);
    }

}
