package toy.pro.shop.web.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.book.dto.response.BookInfoResponseDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;
import toy.pro.shop.web.book.service.BookFindService;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Api(tags = "책 관련 컨트롤러")
public class BookController {

    private final BookFindService bookFindService;

    @ApiOperation(value = "책 검색", notes = "조건에 맞는 책을 조회 합니다. (페이징 적용)")
    @GetMapping("/search")
    public Page<BookSearchResponseDto> searchBooks(@Valid BookSearchRequestDto requestDto)
    {
        return bookFindService.search(requestDto);
    }

    @ApiOperation(value = "책 ID로 단건 검색", notes = "책 ID로 단건을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId", value = "책 ID", required = true)
    })
    @GetMapping("/{bookId}")
    public BookInfoResponseDto searchBook(@PathVariable(value = "bookId") Long bookId)
    {
        return bookFindService.searchBookById(bookId);
    }
}
