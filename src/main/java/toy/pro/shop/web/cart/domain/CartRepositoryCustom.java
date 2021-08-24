package toy.pro.shop.web.cart.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartRepositoryCustom {

    Page<Cart> findCartByMemberId(Long id, Pageable pageable);

    Long updateCartcount(Long id,int count);
}
