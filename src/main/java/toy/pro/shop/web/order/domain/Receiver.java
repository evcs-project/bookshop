package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Receiver {
    @Column(name = "receiver_name")
    private String name;
    @Column(name = "receiver_phone")
    private String phoneNumber;

    protected Receiver(){}

    public Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
