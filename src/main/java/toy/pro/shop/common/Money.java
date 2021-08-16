package toy.pro.shop.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;


@NoArgsConstructor
@Getter
@Embeddable
@Access(AccessType.FIELD)
public class Money {
    private String value;

    public Money(String value) {
        this.value = value;
    }

    public Integer getVal()
    {
        int wonIdx = this.value.lastIndexOf("원");
        String result =  this.value.substring(0, wonIdx);
        return Integer.parseInt(result);
    }

    public Money multiply(int multiplier)
    {
        int moneyVal = getVal() * multiplier;
        return new Money(addWon(moneyVal));
    }

    public static String addWon(int moneyVal)
    {
        return moneyVal + "원";
    }

}
