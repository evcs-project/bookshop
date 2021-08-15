package toy.pro.shop.web.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {
    DELIVERY_BEFORE("배송 전"),
    DELIVERYING("배송 중"),
    DELIVERYING_FINISH("배송 완료");
    private String name;
}
