package toy.pro.shop.web.book.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import toy.pro.shop.web.book.dto.BookSearchDto;
import toy.pro.shop.web.book.dto.BookSearchRequestDto;

import javax.persistence.EntityManager;
import java.util.List;

import static toy.pro.shop.web.book.domain.QBook.*;

public class BookRepositoryImpl implements BookRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public BookRepositoryImpl(EntityManager em)
    {
        this.jpaQueryFactory =new JPAQueryFactory(em);
    }

    @Override
    public Page<Book> searchByBookNm(String bookNm, Pageable pageable) {
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
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
        QueryResults<Book> bookQueryResults = jpaQueryFactory.selectFrom(book)
                .where(writercontain(writer))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Book> results = bookQueryResults.getResults();
        long total = bookQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<BookSearchDto> search(BookSearchRequestDto requestDto)
    {
        PageRequest pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize());

        QueryResults<BookSearchDto> results = jpaQueryFactory
                .select(
                        Projections.constructor(BookSearchDto.class,
                                book.bookId,
                                book.bookNm,
                                book.writer,
                                book.publisher,
                                book.isbn,
                                book.section
                        )
                )
                .from(book)
                .where(
                        bookNmEq(requestDto.getBookNm()),
                        bookWriterEq(requestDto.getWriter()),
                        bookPublishEq(requestDto.getPublisher()),
                        bookIsbnEq(requestDto.getIsbn())
                )
                .offset(getSearchSaveBookOffset(pageable))
                .limit(pageable.getPageSize())
                .orderBy(book.bookId.asc())
                .fetchResults();


        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression bookIsbnEq(String isbn)
    {
        return ObjectUtils.isEmpty(isbn) ? null : book.isbn.contains(isbn);
    }

    private BooleanExpression bookPublishEq(String publisher) {
        return ObjectUtils.isEmpty(publisher) ? null : book.publisher.contains(publisher);
    }

    private long getSearchSaveBookOffset(PageRequest pageable) {
        if (ObjectUtils.isEmpty(pageable))
            return 0;
        return (long) pageable.getPageNumber() * pageable.getPageSize();
    }

    private BooleanExpression bookWriterEq(String writer) {
        return ObjectUtils.isEmpty(writer) ? null : book.writer.contains(writer);
    }

    private BooleanExpression bookNmEq(String bookNm) {
        return ObjectUtils.isEmpty(bookNm) ? null : book.bookNm.contains(bookNm);
    }

    public BooleanExpression bookNmcontain(String bookNm){
       return bookNm != null ? book.bookNm.contains(bookNm) : null;
    }
    public BooleanExpression writercontain(String writer){
        return writer != null ? book.writer.contains(writer) : null;
    }

}
