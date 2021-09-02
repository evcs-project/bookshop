package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Embeddable
public class ShippingInfoRequest {

    @Embedded
    private Receiver receiver;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "shipping_addr")),
            @AttributeOverride(name = "detailAddress", column = @Column(name = "shipping_detail_addr")),
    })
    private Address address;

    protected ShippingInfoRequest(){}

    public ShippingInfoRequest(Receiver receiver, Address address)
    {
        this.receiver = receiver;
        this.address = address;
    }
}
