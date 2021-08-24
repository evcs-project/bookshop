package toy.pro.shop.web.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartRegistRequestDto {

    private Long Bookid;
    private int count;
    private Long Memberid;
}
