package toy.pro.shop.web.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Access(AccessType.FIELD)
@Embeddable
@Getter
public class MemberId implements Serializable {
    private Long id;

    public MemberId(Long id) {
        this.id = id;
    }
}
