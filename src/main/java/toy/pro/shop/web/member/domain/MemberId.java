package toy.pro.shop.web.member.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
public class MemberId implements Serializable {
    @Column(name = "member_id")
    private Long id;
}
