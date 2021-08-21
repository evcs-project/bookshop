package toy.pro.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.pro.shop.web.book.dto.BookSearchDto;
import toy.pro.shop.web.book.dto.BookSearchRequestDto;
import toy.pro.shop.web.book.service.BookFindService;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookFindService bookFindService;

    @GetMapping("/search")
    public Page<BookSearchDto> searchBooks(BookSearchRequestDto requestDto)
    {
        return bookFindService.search(requestDto);
    }
}
