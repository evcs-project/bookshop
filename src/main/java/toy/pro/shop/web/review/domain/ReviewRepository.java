package toy.pro.shop.web.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select r from Review r join r.book b where b.bookId = :bookId")
    List<Review> findReviewByBookId(@Param("bookId") String bookId);
}
