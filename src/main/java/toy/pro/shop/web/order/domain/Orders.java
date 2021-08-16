package toy.pro.shop.web.order.domain;

import lombok.Getter;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.member.domain.MemberId;

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

    public Orders(
            List<OrdersBook> ordersBookList, OrderState orderState, Orderer orderer, ShippingInfo shippingInfo)
    {
        this.ordersBookList = ordersBookList;
        this.orderState = orderState;
        this.orderer = orderer;
        this.shippingInfo = shippingInfo;
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrdersBook> orderLines)
    {
        if (orderLines == null || orderLines.isEmpty())
        {
            throw new IllegalArgumentException("책 주문 최소 1개 이상이여야 합니다.");
        }
    }

    /*private void calculateTotalAmounts() {
        this.totalPrice = new Money(ordersBookList.stream()
                .mapToInt(x -> x.getAmounts().getValue()).sum());
    }*/

}
