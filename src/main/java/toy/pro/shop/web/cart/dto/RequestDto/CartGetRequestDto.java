package toy.pro.shop.web.cart.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartGetRequestDto {

    int page;
    int size;
    Long id;
}
