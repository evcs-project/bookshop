package toy.pro.shop.web.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReceiverRequestDto {
    private String name;
    private String phoneNumber;

    protected ReceiverRequestDto(){}

    public ReceiverRequestDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
