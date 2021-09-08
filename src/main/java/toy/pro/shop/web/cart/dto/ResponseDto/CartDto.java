package toy.pro.shop.web.cart.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toy.pro.shop.web.cart.domain.Cart;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartDto {
    private String bookName;
    private int count;
    private String writer;
    private int price;

    public static CartDto toCartDto(Cart cart)
    {
        return CartDto.builder()
                .bookName(cart.getBook().getBookNm())
                .count(cart.getCount())
                .writer(cart.getBook().getWriter())
                .price(cart.getPrice())
                .build();
    }
}
