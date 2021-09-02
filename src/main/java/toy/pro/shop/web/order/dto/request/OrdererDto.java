package toy.pro.shop.web.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toy.pro.shop.web.member.domain.MemberId;
import toy.pro.shop.web.order.domain.Orderer;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdererDto {
    private Long memberId;
    private String name;

    public Orderer toEntity()
    {
        return new Orderer(new MemberId(memberId), name);
    }
}
