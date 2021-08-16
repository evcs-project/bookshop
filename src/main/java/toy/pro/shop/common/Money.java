package toy.pro.shop.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Money {
    private String value;

    public Money(String value) {
        this.value = value;
    }
}
