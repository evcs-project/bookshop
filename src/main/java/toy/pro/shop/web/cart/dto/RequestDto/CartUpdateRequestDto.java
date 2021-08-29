package toy.pro.shop.web.cart.dto.RequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartUpdateRequestDto {


    @ApiModelProperty(value = "카트 ID", notes = "수정할 카트 ID",required = true)
    @NotNull(message = "카트 Id를 입력해주세요")
    private Long Cartid;


    @ApiModelProperty(value = "책 수량", example = "1", notes = "수량의 최소값은 1입니다..", required = true)
    @NotNull(message = "수량을 입력해주세요")
    @Min(value = 1, message = "최소 수량은 1개 입니다.")
    private int count;
}
