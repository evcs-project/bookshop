package toy.pro.shop.web.cart.dto.RequestDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartUpdateRequestDto {

    @NotNull(message = "수량을 입력해주세요.")
    private int count;
}
