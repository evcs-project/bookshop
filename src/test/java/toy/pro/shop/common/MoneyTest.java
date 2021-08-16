package toy.pro.shop.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MoneyTest {

    @Test
    void getValTest()
    {
        Money money = new Money("123300원");
        Integer val = money.getVal();

        assertEquals(123300, val);
    }
}
