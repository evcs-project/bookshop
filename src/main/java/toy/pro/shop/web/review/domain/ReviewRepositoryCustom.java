package toy.pro.shop.web.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.pro.shop.web.cart.domain.Cart;


public interface ReviewRepositoryCustom {

    Page<Review> findReviewByMemberEmail(String userEmail, Pageable pageable);

}
