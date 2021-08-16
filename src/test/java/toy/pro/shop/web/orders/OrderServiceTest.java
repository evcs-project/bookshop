package toy.pro.shop.web.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.pro.shop.web.order.domain.OrderRepository;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

}
