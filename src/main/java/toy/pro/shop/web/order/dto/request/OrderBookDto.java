package toy.pro.shop.web.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.book.domain.BookId;
import toy.pro.shop.web.order.domain.OrdersBook;

@Getter
@Setter
@AllArgsConstructor
public class OrderBookDto {

    private BookId bookId;
    private Integer quantity;

    public OrdersBook toOrdersBook()
    {
        return OrdersBook.createOrdersBook(bookId, new Money(0), quantity);
    }

}
