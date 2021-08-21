package toy.pro.shop.web.book.domain;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class BookId implements Serializable {
    @Column(name = "book_id")
    private Long id;
}
