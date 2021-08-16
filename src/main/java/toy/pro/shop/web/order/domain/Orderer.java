package toy.pro.shop.web.order.domain;

import toy.pro.shop.web.member.domain.MemberId;

import javax.persistence.*;

@Embeddable
public class Orderer {

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "orderer_id")))
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;
}
