package toy.pro.shop.web.review.dto.RequestDto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewUpdateRequestDto {

    @NotNull(message = "리뷰 제목을 입력해주세요.")
    private String title;

    @NotNull(message = "리뷰 내용을 입력해주세요.")
    private String content;
}
