package toy.pro.shop.web.book.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchRequestDto {

    @ApiModelProperty(value = "사이즈", example = "1", notes = "사이즈 최소값은 1입니다.", required = true)
    @NotNull(message = "사이즈 미입력")
    @Min(value = 1, message = "최소 사이즈는 1입니다.")
    private Integer size;

    @ApiModelProperty(value = "페이지 넘버", example = "0", notes = "페이지 최소값은 0입니다.", required = true)
    @NotNull(message = "페이지 넘버 미입력")
    @Min(value = 0, message = "최소 페이지 넘버는 0입니다.")
    private Integer page;

    @ApiModelProperty(value = "책 이름",notes = "책 검색 파라미터 (default)")
    private String bookNm;

    @ApiModelProperty(value = "출판사", notes = "출판사 검색 파라미터")
    private String publisher;

    @ApiModelProperty(value = "ISBN", notes = "ISBN 검색 파라미터")
    private String isbn;

    @ApiModelProperty(value = "작가",  notes = "작가 이름 검색 파라미터")
    private String writer;

    @ApiModelProperty(value = "카테고리 ID", notes = "카테고리 ID 검색 파라미터")
    private Long categoryId;
}
