package toy.pro.shop.web.order.domain;

import lombok.Getter;
import toy.pro.shop.common.Money;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    @ElementCollection
    @CollectionTable(name = "orders_book", joinColumns = @JoinColumn(name = "orders_book_id"))
    @OrderColumn(name = "order_book_idx")
    private List<OrdersBook> ordersBookList;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "total_price"))})
    private Money totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Embedded
    private Orderer orderer;

    @Embedded
    private ShippingInfo shippingInfo;

}
