package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String address1;
    private String address2;
    private String zipCode;

    protected Address(){}

    public Address(String address1, String address2, String zipCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.zipCode = zipCode;
    }

    public Address(String address2, String zipCode) {
        this.address2 = address2;
        this.zipCode = zipCode;
    }
}
