package toy.pro.shop.web.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
public class BookTest {

    @Autowired
    BookRepository bookRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void test1(){

    }
}
