package toy.pro.shop.web.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewRepositoryCustom {
    // 회원별 리뷰 조회
    Page<Review> findReviewByMemberId(Long id, Pageable pageable);
}
