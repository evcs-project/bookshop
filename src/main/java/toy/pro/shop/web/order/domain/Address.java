package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String address;
    private String detailAddress;

    protected Address(){}

    public Address(String address, String detailAddress) {
        this.address = address;
        this.detailAddress = detailAddress;
    }

}
