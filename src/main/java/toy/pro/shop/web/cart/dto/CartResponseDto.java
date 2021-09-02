package toy.pro.shop.web.cart.dto;

import lombok.*;
import toy.pro.shop.web.cart.domain.Cart;

@Getter
@Setter
@NoArgsConstructor
public class CartResponseDto {

    private String bookname;
    private int count;
    private String writer;
    private int price;

    @Builder
    private CartResponseDto(String bookname, int count, String writer, int price)
    {
        this.bookname = bookname;
        this.count = count;
        this.writer = writer;
        this.price = price;
    }

    public static CartResponseDto toResponseDto(Cart cart)
    {
        return CartResponseDto.builder()
                .bookname(cart.getBook().getBookNm())
                .count(cart.getCount())
                .writer(cart.getBook().getWriter())
                .price(cart.getCount()* cart.getBook().getPrice().getValue())
                .build();
    }

}
