package toy.pro.shop.web.order.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class OrdersBook {

    @Id
    @GeneratedValue
    private Long ordersBook_Id;



}
