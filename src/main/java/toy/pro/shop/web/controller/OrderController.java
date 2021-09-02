package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.order.dto.request.OrderRequestDto;
import toy.pro.shop.web.order.dto.response.OrderSearchResponse;
import toy.pro.shop.web.order.service.OrderService;

import javax.validation.Valid;

@Api(tags = "주문 컨트롤러")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "주문 하기")
    @PostMapping
    public void order(@Valid @RequestBody OrderRequestDto orderRequestDto)
    {
        orderService.order(orderRequestDto);
    }

    @ApiOperation(value = "주문 ID로 조회")
    @GetMapping("/{orderId}")
    public OrderSearchResponse gerOrderById(@PathVariable(value = "orderId") Long orderId)
    {
        return orderService.getOrderById(orderId);
    }

}
