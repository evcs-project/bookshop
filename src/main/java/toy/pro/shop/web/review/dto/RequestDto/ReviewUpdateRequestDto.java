package toy.pro.shop.web.review.dto.RequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    @ApiModelProperty(value = "리뷰 제목")
    @NotBlank(message = "리뷰 제목을 입력해주세요.")
    private String title;

    @ApiModelProperty(value = "리뷰 내용")
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String content;

    @Builder
    public ReviewUpdateRequestDto(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
