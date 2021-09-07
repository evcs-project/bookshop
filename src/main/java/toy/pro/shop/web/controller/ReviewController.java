package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegisterRequestDto;
import toy.pro.shop.web.review.dto.RequestDto.ReviewUpdateRequestDto;
import toy.pro.shop.web.review.dto.ResponseDto.BookReviewResponseDto;
import toy.pro.shop.web.review.dto.ResponseDto.MemberReviewResponseDto;
import toy.pro.shop.web.review.service.ReviewService;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/review")
@Api(tags = "리뷰 관련 api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("리뷰 등록하기")
    @PostMapping
    public void reviewRegister(@RequestBody @Valid ReviewRegisterRequestDto requestDto) {
        reviewService.reviewRegister(requestDto);
    }

    @ApiOperation("리뷰 수정하기")
    @PutMapping("/{reviewId}")
    public void reviewUpdate(@PathVariable(value = "reviewId") Long reviewId,
                             @RequestBody @Valid ReviewUpdateRequestDto requestDto) {
        reviewService.reviewUpdate(reviewId, requestDto);
    }

    @ApiOperation("리뷰 삭제하기")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable(value = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @ApiOperation("특정 책의 리뷰 리스트 가져오기")
    @GetMapping("/{bookId}")
    public BookReviewResponseDto getBookReviewByBookId(@PathVariable(value = "bookId") Long bookId) {
        return reviewService.findBookReview(bookId);
    }

    @ApiOperation("특정 유저의 리뷰 리스트 가져오기")
    @GetMapping("/myReview")
    public MemberReviewResponseDto getMemberReview() {
        return reviewService.findMemberReview();
    }
}
