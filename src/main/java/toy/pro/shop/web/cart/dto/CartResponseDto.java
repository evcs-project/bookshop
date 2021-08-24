package toy.pro.shop.web.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toy.pro.shop.web.cart.domain.Cart;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartResponseDto {

    private String bookname;
    private int count;
    private String writer;
    private int price;

    public static CartResponseDto toResponseDto(Cart cart){
        return CartResponseDto.builder()
                .bookname(cart.getBook().getBookNm())
                .count(cart.getCount())
                .writer(cart.getBook().getWriter())
                .price(cart.getCount()*cart.getBook().getPrice().getValue())
                .build();
    }

}
