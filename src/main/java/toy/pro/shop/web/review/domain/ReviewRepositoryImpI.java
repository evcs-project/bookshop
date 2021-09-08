package toy.pro.shop.web.review.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static toy.pro.shop.web.book.domain.QBook.book;
import static toy.pro.shop.web.member.domain.QMember.member;
import static toy.pro.shop.web.review.domain.QReview.review;

public class ReviewRepositoryImpI implements ReviewRepositoryCustom{

    @Autowired
    EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRepositoryImpI(EntityManager entityManager)
    {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Review> findReviewByMemberEmail(String userEmail, Pageable pageable)
    {
        QueryResults<Review> reviewQueryResults = jpaQueryFactory.selectFrom(review)
                .join(review.member, member)
                .where(member.email.eq(userEmail))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        long total = reviewQueryResults.getTotal();
        List<Review> results = reviewQueryResults.getResults();
        return new PageImpl<>(results,pageable,total);
    }

}
