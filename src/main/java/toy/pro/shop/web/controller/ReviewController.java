package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegisterRequestDto;
import toy.pro.shop.web.review.dto.RequestDto.ReviewUpdateRequestDto;
import toy.pro.shop.web.review.dto.ResponseDto.ReviewResponseDto;
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
    public void reviewRegister(@RequestBody @Valid ReviewRegisterRequestDto requestDto)
    {
        reviewService.reviewRegister(requestDto);
    }


    @ApiOperation("리뷰 수정하기")
    @PutMapping("/{reviewId}")
    public void reviewUpdate(@PathVariable(value = "reviewId") Long reviewId,
                             @RequestBody @Valid ReviewUpdateRequestDto reviewUpdateRequestDto)
    {
        reviewService.reviewUpdate(reviewId,reviewUpdateRequestDto);
    }

    @ApiOperation("리뷰 삭제하기")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable(value = "reviewId") Long reviewId)
    {
        reviewService.deleteReview(reviewId);
    }

    @ApiOperation("리뷰 조회하기")
    @GetMapping("/{reviewId}")
    public ReviewResponseDto findByReviewId(@PathVariable(value = "reviewId") Long reviewId)
    {
        return reviewService.findByReviewId(reviewId);
    }

}
