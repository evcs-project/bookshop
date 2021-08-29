package toy.pro.shop.web.review.dto.ResponseDto;

import lombok.Getter;
import toy.pro.shop.web.review.domain.Review;

@Getter
public class ReviewResponseDto {
    private Long reviewId;
    private String title;
    private String content;

    // Entity를 받아 필드에 값을 넣음
    public ReviewResponseDto(Review entity)
    {
        this.reviewId = entity.getReviewId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
