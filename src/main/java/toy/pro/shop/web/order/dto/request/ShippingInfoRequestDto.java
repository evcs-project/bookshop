package toy.pro.shop.web.order.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import toy.pro.shop.web.order.domain.Address;
import toy.pro.shop.web.order.domain.Receiver;
import toy.pro.shop.web.order.domain.ShippingInfoRequest;

import javax.validation.constraints.NotNull;

@Getter
public class ShippingInfoRequestDto {

    @ApiModelProperty(value = "주문자 이름", example = "홍길동", notes = "주문자의 이름 입력(아이디 로그인 사람과 동일하지 않아도됨)"
            , required = true)
    @NotNull(message = "주문자의 이름을 입력해주세요")
    private String name;

    @ApiModelProperty(value = "주문자 연락 번호", example = "010-0000-0000", notes = "주문자의 핸드폰 번호 입력")
    @NotNull(message = "주문자의 연락 번호를 입력해주세요")
    private String phoneNumber;

    @ApiModelProperty(value = "배송 주소", notes = "배송 주소 (자세히 말고)", example = "인천광역시 남동구"
            , required = true)
    @NotNull(message = "배송 주소를 입력해주세요")
    private String address;

    @ApiModelProperty(value = "상세 배송 주소", notes = "동 호수 같은 디테일 주소", example = "101동 101호"
            , required = true)
    private String detailAddress;

    public ShippingInfoRequest toEntity()
    {
        return new ShippingInfoRequest(new Receiver(name, phoneNumber), new Address(address, detailAddress));
    }
}
