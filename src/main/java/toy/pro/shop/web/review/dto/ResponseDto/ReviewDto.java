package toy.pro.shop.web.review.dto.ResponseDto;

import lombok.*;
import toy.pro.shop.web.book.domain.Book;
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
    private Long bookId;
    private Long memberId;

    public ReviewDto(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public static ReviewDto toReviewDto(Review review)
    {
        Book book = review.getBook();
        Member member = review.getMember();

        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .title(review.getTitle())
                .bookId(book.getBookId())
                .memberId(member.getId())
                .build();
    }
}
