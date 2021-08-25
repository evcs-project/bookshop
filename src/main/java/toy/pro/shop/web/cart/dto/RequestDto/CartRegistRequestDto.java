package toy.pro.shop.web.cart.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartRegistRequestDto {

    private Long Bookid;

    @NotNull(message = "수량을 입력해주세요")
    private int count;

    private Long Memberid;
}
