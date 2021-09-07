package toy.pro.shop.web.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.pro.shop.web.cart.domain.Cart;


public interface ReviewRepositoryCustom {
    // 회원별 리뷰 조회
    Page<Review> findReviewByMemberEmail(String userEmail, Pageable pageable);
//    // 책별 리뷰 조회
//    Page<Review> findReviewByBookId(Long bookId, Pageable pageable);
}
