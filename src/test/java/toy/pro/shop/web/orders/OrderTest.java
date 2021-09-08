package toy.pro.shop.web.orders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.book.domain.BookId;
import toy.pro.shop.web.member.domain.MemberId;
import toy.pro.shop.web.order.domain.*;
import toy.pro.shop.web.order.dto.request.OrdererDto;
import toy.pro.shop.web.order.dto.request.ShippingInfoRequestDto;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderTest {


    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Commit
    void createOrder()
    {
        OrdersBook ordersBook = new OrdersBook(new BookId(1L), new Money(12300), 1);

        List<OrdersBook> ordersBooks = new ArrayList<>();
        ordersBooks.add(ordersBook);

        OrdererDto orderer = new OrdererDto(1L, "TestUser");

        Orders order = Orders.createOrder(ordersBooks, orderer, new ShippingInfoRequestDto());

        Assertions.assertEquals(1L, order.getOrderer().getMemberId().getId());

    }

}
