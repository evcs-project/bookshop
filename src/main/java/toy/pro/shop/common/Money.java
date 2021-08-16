package toy.pro.shop.common;

import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Access(AccessType.FIELD)
public class Money {
    private int value;

    public Money(int value)
    {
        this.value = value;
    }

    public Money multiply(int multiplier)
    {
        return new Money(value * multiplier);
    }

    public int getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return Integer.toString(value);
    }
}
