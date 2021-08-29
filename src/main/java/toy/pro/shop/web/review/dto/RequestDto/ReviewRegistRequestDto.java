package toy.pro.shop.web.review.dto.RequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.review.domain.Review;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegistRequestDto {

    private Long bookId;
    private Long memberId;

    @ApiModelProperty(value = "리뷰 제목")
    @NotBlank(message = "리뷰 제목을 입력해주세요.")
    private String title;

    @ApiModelProperty(value = "리뷰 내용")
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String content;

    @Builder
    public ReviewRegistRequestDto(String title, String content)
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


