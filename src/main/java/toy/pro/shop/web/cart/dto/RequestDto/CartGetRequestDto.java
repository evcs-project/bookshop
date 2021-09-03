package toy.pro.shop.web.cart.dto.RequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartGetRequestDto {


    @ApiModelProperty(value = "페이지 넘버", example = "0", notes = "페이지 최소값은 0입니다.", required = true)
    @NotNull(message = "페이지 넘버를 입력해주세요")
    @Min(value = 0, message = "최소 페이지 넘버는 0입니다.")
    private int page;

    @ApiModelProperty(value = "사이즈", example = "1", notes = "사이즈 최소값은 1입니다.", required = true)
    @NotNull(message = "사이즈를 입력해주세요")
    @Min(value = 1, message = "최소 사이즈는 1입니다.")
    private int size;

}
