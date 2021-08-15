package toy.pro.shop.web.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> , BookRepositoryCustom{

}
