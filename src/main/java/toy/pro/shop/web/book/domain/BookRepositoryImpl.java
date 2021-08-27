package toy.pro.shop.web.book.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ObjectUtils;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;

import javax.persistence.EntityManager;

import static toy.pro.shop.web.book.domain.QBook.*;

public class BookRepositoryImpl implements BookRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public BookRepositoryImpl(EntityManager em)
    {
        this.jpaQueryFactory =new JPAQueryFactory(em);
    }

    @Override
    public Page<BookSearchResponseDto> search(BookSearchRequestDto requestDto)
    {
        PageRequest pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize());

        QueryResults<BookSearchResponseDto> results = jpaQueryFactory
                .select(
                        Projections.constructor(
                                BookSearchResponseDto.class,
                                book.bookId,
                                book.bookNm,
                                book.writer,
                                book.publisher,
                                book.isbn,
                                book.thumbNail,
                                book.category.categoryId,
                                book.category.categoryName,
                                book.price.value.as("price")
                        )
                )
                .from(book)
                .innerJoin(book.category)
                .where(
                        bookNmEq(requestDto.getBookNm()),
                        bookWriterEq(requestDto.getWriter()),
                        bookPublishEq(requestDto.getPublisher()),
                        bookIsbnEq(requestDto.getIsbn()),
                        cateIdEq(requestDto.getCategoryId())
                )
                .offset(getSearchSaveBookOffset(pageable))
                .limit(pageable.getPageSize())
                .orderBy(book.bookId.asc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression cateIdEq(Long categoryId)
    {
        return ObjectUtils.isEmpty(categoryId) ? null : book.category.categoryId.eq(categoryId);
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

}
