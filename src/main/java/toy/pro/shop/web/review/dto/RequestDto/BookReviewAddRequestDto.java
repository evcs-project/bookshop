package toy.pro.shop.web.review.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.pro.shop.web.review.domain.Review;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewAddRequestDto {
    @NotNull(message = "review 내용을 입력해주세요.")
    private @Valid ReviewRequestDto reviewDto;

    @NotBlank(message = "bookId (책 ID)를 입력해주세요.")
    private String bookId;

    public Review toEntity()
    {
        return  Review.creteReview(reviewDto.getContent(), reviewDto.getTitle());
    }
}
