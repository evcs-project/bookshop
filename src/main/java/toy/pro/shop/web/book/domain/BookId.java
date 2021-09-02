package toy.pro.shop.web.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor
public class BookId implements Serializable {
    @Column(name = "book_id")
    private Long id;

    public BookId(Long id) {
        this.id = id;
    }
}
