package toy.pro.shop.web.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class BookId implements Serializable {
    @Column(name = "book_id")
    private Long id;
}
