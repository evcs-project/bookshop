package toy.pro.shop.web.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name IN :names")
    List<Role> findByNames(@Param("names") List<RoleName> names);
}
