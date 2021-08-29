package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegistRequestDto;
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
    public void reviewRegist(@RequestBody @Valid ReviewRegistRequestDto reviewRegistRequestDto)
    {
        reviewService.reviewRegist(reviewRegistRequestDto);
    }

    @ApiOperation("리뷰 수정하기")
    @PutMapping("/{reivewId}")
    public void reviewUpdate(@PathVariable(value = "reivewId") Long id,
                             @RequestBody @Validated ReviewUpdateRequestDto reviewUpdateRequestDto)
    {
        reviewService.reviewUpdate(id,reviewUpdateRequestDto);
    }

    @ApiOperation("리뷰 삭제하기")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable(value = "reviewId") Long id)
    {
        reviewService.deleteReview(id);
    }

    // TODO : 수정
    @ApiOperation("리뷰 조회하기")
    @GetMapping("/{reviewId}")
    public ReviewResponseDto findbyId(@PathVariable(value = "reviewId") Long id)
    {
        return reviewService.findById(id);
    }

}
