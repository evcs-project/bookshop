package toy.pro.shop.web.review.dto.RequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.member.domain.Member;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRegistRequestDto {

    private Long bookId;

    @ApiModelProperty(value = "리뷰 제목")
    @NotBlank(message = "리뷰 제목을 입력해주세요.")
    private String title;

    @ApiModelProperty(value = "리뷰 내용")
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String content;

    private Long memberId;

    public ReviewRegistRequestDto(String title, String contet, Long memberId, Long bookId) {
        this.title = title;
        this.content = contet;
        this.memberId = memberId;
        this.bookId = bookId;
    }
}


