package toy.pro.shop.web.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.ObjectUtils;
import toy.pro.shop.common.Money;
import toy.pro.shop.web.order.dto.request.OrdererDto;
import toy.pro.shop.web.order.dto.request.ShippingInfoRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static toy.pro.shop.web.order.domain.OrderState.DELIVERY_BEFORE;

@Entity
@Getter
@Table(name = "orders")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    @ElementCollection
    @CollectionTable(name = "orders_book", joinColumns = @JoinColumn(name = "orders_book_id"))
    @OrderColumn(name = "order_book_idx")
    private List<OrdersBook> ordersBooks;

    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "total_price"))})
    @Embedded
    private Money totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Embedded
    private Orderer orderer;

    @Embedded
    private ShippingInfoRequest shippingInfoRequest;

    private LocalDateTime orderAt;

    private void verifyAtLeastOneOrMoreOrderBooks(List<OrdersBook> orderLines)
    {
        if (ObjectUtils.isEmpty(orderLines))
        {
            throw new IllegalArgumentException("책 주문 최소 1개 이상이여야 합니다.");
        }
    }

    private void calculateTotalAmounts()
    {
        int totalPrice = ordersBooks.stream().mapToInt(ordersBook -> ordersBook.getAmounts().getValue()).sum();
        this.totalPrice = new Money(totalPrice);
    }

    private Orders(List<OrdersBook> ordersBooks, Orderer orderer, ShippingInfoRequest shippingInfoRequest, OrderState orderState)
    {
        setOrderer(orderer);
        setOrderBooks(ordersBooks);
        setShippingInfoRequest(shippingInfoRequest);
        this.orderState = orderState;
        this.orderAt = LocalDateTime.now();
    }

    public static Orders createOrder(
            List<OrdersBook> ordersBooks
            , OrdererDto ordererDto
            , ShippingInfoRequestDto shippingInfoRequestDto)
    {
        ShippingInfoRequest shippingInfoRequest = shippingInfoRequestDto.toEntity();
        Orderer orderer = ordererDto.toEntity();
        return new Orders(ordersBooks, orderer, shippingInfoRequest, DELIVERY_BEFORE);
    }

    private void setShippingInfoRequest(ShippingInfoRequest shippingInfoRequest)
    {
        if (ObjectUtils.isEmpty(shippingInfoRequest))
            throw new IllegalArgumentException("no shippingInfo");
        this.shippingInfoRequest = shippingInfoRequest;
    }

    private void setOrderer(Orderer orderer)
    {
        if (ObjectUtils.isEmpty(orderer))
            throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderBooks(List<OrdersBook> ordersBooks)
    {
        verifyAtLeastOneOrMoreOrderBooks(ordersBooks);
        this.ordersBooks = ordersBooks;
        calculateTotalAmounts();
    }
}
