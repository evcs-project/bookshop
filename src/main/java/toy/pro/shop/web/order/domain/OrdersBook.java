package toy.pro.shop.web.order.domain;

import toy.pro.shop.common.Money;
import toy.pro.shop.web.book.domain.BookId;

import javax.persistence.*;

@Embeddable
public class OrdersBook {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "order_book_id"))})
    private BookId bookId;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "price"))})
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "amounts"))})
    @Embedded
    private Money amounts;
}
