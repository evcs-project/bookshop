package toy.pro.shop.web.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.book.domain.BookId;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class OrdersBook {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "order_book_id"))})
    private BookId bookId;

    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "price"))})
    @Embedded
    private Money price;

    @Column(name = "quantity")
    private Integer quantity;

    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "amounts"))})
    @Embedded
    private Money amounts;

    public OrdersBook(BookId bookId, Money price, int quantity)
    {
        this.bookId = bookId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts()
    {
        return price.multiply(quantity);
    }
}
