package toy.pro.shop.web.order.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.pro.shop.web.order.domain.*;
import toy.pro.shop.web.order.domain.ShippingInfoRequest;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private ShippingInfoRequestDto shippingInfoRequestDto;
    private List<OrderBookDto> orderBookDtoList;

    @JsonIgnore
    public List<OrdersBook> getOrdersBook()
    {
        return orderBookDtoList.stream()
                .map(OrderBookDto::toOrdersBook)
                .collect(Collectors.toList());
    }
}
