package toy.pro.shop.web.member.domain;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Access(AccessType.FIELD)
@Embeddable
public class MemberId implements Serializable {
    private Long id;
}
