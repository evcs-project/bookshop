package toy.pro.shop.web.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {

    public void deleteById(Long id);



}
