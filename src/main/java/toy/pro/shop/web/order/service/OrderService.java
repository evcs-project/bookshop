package toy.pro.shop.web.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.domain.MemberId;
import toy.pro.shop.web.order.domain.OrderRepository;
import toy.pro.shop.web.order.domain.Orderer;
import toy.pro.shop.web.order.domain.Orders;
import toy.pro.shop.web.order.dto.request.OrderRequestDto;
import toy.pro.shop.web.order.dto.request.OrdererDto;
import toy.pro.shop.web.order.dto.response.OrderSearchResponse;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void order(OrderRequestDto orderRequestDto)
    {
//        Orderer orderer =
        Orders order = Orders.createOrder(
                orderRequestDto.getOrdersBook(), new OrdererDto(1L, "TestUer"), orderRequestDto.getShippingInfoRequestDto());

        orderRepository.save(order);
    }

    public OrderSearchResponse getOrderById(Long orderId)
    {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));

        return null;
    }
}
