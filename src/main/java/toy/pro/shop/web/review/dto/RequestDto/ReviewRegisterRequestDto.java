package toy.pro.shop.web.review.dto.RequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import toy.pro.shop.web.review.domain.Review;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegisterRequestDto {

    private Long bookId;
//    private Long memberId;

    @ApiModelProperty(value = "리뷰 제목 ID", notes = "수정할 리뷰 제목 ID", required = true)
    @NotBlank(message = "리뷰 제목을 입력해주세요.")
    private String title;

    @ApiModelProperty(value = "리뷰 내용", notes = "수정할 리뷰 내용", required = true)
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String content;

    @Builder
    public ReviewRegisterRequestDto(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public Review toEntity()
    {
        return Review.builder()
                .title(title)
                .content(content)
                .build();
    }
}

