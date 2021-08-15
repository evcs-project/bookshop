package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;
    private LocalDateTime orderDate;
    private String orderAddress;




}
