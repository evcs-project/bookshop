package toy.pro.shop.web.book.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public BookRepositoryImpl(EntityManager em)
    {
        this.jpaQueryFactory =new JPAQueryFactory(em);
    }

    @Override
    public Page<Book> searchByBookNm(String bookNm, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(QBook.book)
                .where(bookNmcontain(bookNm))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Book> searchByWriter(String writer, Pageable pageable)
    {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(QBook.book)
                .where(writercontain(writer))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    public BooleanExpression bookNmcontain(String bookNm){
       return bookNm != null ? QBook.book.bookNm.contains(bookNm) : null;
    }
    public BooleanExpression writercontain(String writer){
        return writer != null ? QBook.book.writer.contains(writer) : null;
    }

}
