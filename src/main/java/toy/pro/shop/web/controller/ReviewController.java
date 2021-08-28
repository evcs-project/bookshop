package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.review.dto.RequestDto.ReviewUpdateRequestDto;
import toy.pro.shop.web.review.service.ReviewService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/review")
@Api(tags = "리뷰 관련 api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("리뷰 수정하기")
    @PutMapping("/{reviewId}")
    public void updateReview(@PathVariable(value = "reviewId") Long id,
                             @RequestBody @Validated ReviewUpdateRequestDto reviewUpdateRequestDto)
    {
        reviewService.updateReview(id,reviewUpdateRequestDto);
    }

    @ApiOperation("리뷰 삭제하기")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable(value = "reviewId") Long id)
    {
        reviewService.deleteReview(id);
    }

}
