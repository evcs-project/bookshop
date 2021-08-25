package toy.pro.shop.web.cart.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toy.pro.shop.web.cart.dto.ResponseDto.CartDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartResponseDto {

    List<CartDto> cartDtoList;

}
