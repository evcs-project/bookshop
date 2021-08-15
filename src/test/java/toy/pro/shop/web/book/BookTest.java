package toy.pro.shop.web.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Book> book = bookRepository.searchByBookNm("이", pageRequest);
        List<Book> content = book.getContent();
        for (Book book1 : content) {
            System.out.println(book1.getBookNm());
        }
    }
}
