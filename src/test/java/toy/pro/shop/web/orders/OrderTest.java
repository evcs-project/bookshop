package toy.pro.shop.web.orders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.book.domain.BookId;
import toy.pro.shop.web.member.domain.MemberId;
import toy.pro.shop.web.order.domain.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderTest {


    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder()
    {
        OrdersBook ordersBook = new OrdersBook(new BookId(1L), new Money(12300), 1);

        List<OrdersBook> ordersBooks = new ArrayList<>();
        ordersBooks.add(ordersBook);

        Receiver receiver = new Receiver("받는사람", "01230");

        Address address = new Address("12312", "123213");

        Orderer orderer = new Orderer(new MemberId(1L), "TestUser");

        Orders order = Orders.createOrder(ordersBooks, orderer, new ShippingInfoRequest(receiver, address));

        orderRepository.save(order);

        Assertions.assertEquals(1L, order.getOrderer().getMemberId().getId());
        Assertions.assertEquals("받는사람", order.getShippingInfoRequest().getReceiver().getName());
    }

}
