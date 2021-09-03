package toy.pro.shop.web.cart.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static toy.pro.shop.web.cart.domain.QCart.*;
import static toy.pro.shop.web.member.domain.QMember.member;

public class CartRepositoryImpl implements CartRepositoryCustom {


    @Autowired
    EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public CartRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Cart> findCartByMemberemail(String useremail, Pageable pageable) {
        QueryResults<Cart> cartQueryResults = jpaQueryFactory.selectFrom(cart)
                .join(cart.member, member)
                .where(member.email.eq(useremail))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        long total = cartQueryResults.getTotal();
        List<Cart> results = cartQueryResults.getResults();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Long updateCartcount(Long cartid, int count) {
        long execute = jpaQueryFactory.update(cart)
                .set(cart.count, count)
                .where(cart.cartId.eq(cartid))
                .execute();
        entityManager.flush();
        entityManager.clear();
        return execute;
    }


}
