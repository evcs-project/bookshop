package toy.pro.shop.web.review.dto.ResponseDto;

import lombok.*;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.review.domain.Review;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long reviewId;
    private String title;
    private String content;
    private Long memberId;
    private Long bookId;

//    public ReviewDto(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    public static ReviewDto toReviewDto(Review review) {

        Member member = review.getMember();

        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .title(review.getTitle())
                .content(review.getContent())
                .memberId(member.getId())
                .bookId(review.getBook().getBookId())
                .build();
    }
}
