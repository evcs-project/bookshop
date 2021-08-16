package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Embeddable
public class ShippingInfo {

    @Embedded
    private Receiver receiver;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(name = "shipping_addr")),
            @AttributeOverride(name = "address2", column = @Column(name = "shipping_detail_addr")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code"))
    })
    private Address address;

    protected ShippingInfo(){}

    public ShippingInfo(Receiver receiver, Address address)
    {
        this.receiver = receiver;
        this.address = address;
    }
}
