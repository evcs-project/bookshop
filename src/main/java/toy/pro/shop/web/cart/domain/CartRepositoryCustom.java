package toy.pro.shop.web.cart.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartRepositoryCustom {

    Page<Cart> findCartByMemberemail(String useremail, Pageable pageable);

    Long updateCartcount(Long cartid,int count);


}
