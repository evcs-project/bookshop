package toy.pro.shop.web.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    private String categoryName;
}
