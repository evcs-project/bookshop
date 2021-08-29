package toy.pro.shop.web.review.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;
import toy.pro.shop.web.review.domain.Review;
import toy.pro.shop.web.review.domain.ReviewRepository;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegistRequestDto;
import toy.pro.shop.web.review.dto.RequestDto.ReviewUpdateRequestDto;
import toy.pro.shop.web.review.service.ReviewService;

import static org.assertj.core.api.Assertions.*;

import java.util.List;


@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @Test
    @DisplayName("리뷰 등록 테스트")
    public void ReviewRegist()
    {
        memberRepository.save(new Member("test1"));
        memberRepository.save(new Member("test2"));

        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();

        Long memId1 = memberRepository.findByname("test1").getId();
        Long memId2 = memberRepository.findByname("test2").getId();

        Long aLong = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"aaa","111"));
        Long aLong1 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"bbb","222"));
        Long aLong2 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"ccc","333"));

        Long aLong3 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId2,"ddd","444"));
        Long aLong4 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId2,"eee","555"));

        PageRequest of = PageRequest.of(0, 3);

        List<Review> all = reviewRepository.findAll();

        assertThat(all.size()).isEqualTo(5);

        Page<Review> reviewByMemberId = reviewRepository.findReviewByMemberId(memId1, of);
        Page<Review> reviewByMemberId1 = reviewRepository.findReviewByMemberId(memId2, of);

        assertThat(reviewByMemberId.getContent().size()).isEqualTo(3);
        assertThat(reviewByMemberId.getContent().get(0).getTitle()).isEqualTo("aaa");
        assertThat(reviewByMemberId.getContent().get(1).getTitle()).isEqualTo("bbb");
        assertThat(reviewByMemberId.getContent().get(2).getTitle()).isEqualTo("ccc");

        assertThat(reviewByMemberId1.getContent().size()).isEqualTo(2);
        assertThat(reviewByMemberId1.getContent().get(0).getTitle()).isEqualTo("ddd");
        assertThat(reviewByMemberId1.getContent().get(1).getTitle()).isEqualTo("eee");
    }

    @DisplayName("리뷰 수정하기")
    @Test
    public void ReviewUpdate()
    {
        memberRepository.save(new Member("test1"));
        memberRepository.save(new Member("test2"));

        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();

        Long memId1 = memberRepository.findByname("test1").getId();
        Long memId2 = memberRepository.findByname("test2").getId();

        Long aLong = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"aaa","111"));
        Long aLong1 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"bbb","222"));
        Long aLong2 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"ccc","333"));

        Long aLong3 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId2,"ddd","444"));
        Long aLong4 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId2,"eee","555"));

        reviewService.reviewUpdate(aLong,ReviewUpdateRequestDto.builder().title("new").content("new1").build());

        Review review = reviewRepository.findById(aLong).get();
        assertThat(review.getTitle()).isEqualTo("new");
        assertThat(review.getContent()).isEqualTo("new1");
    }

    @DisplayName("리뷰 삭제")
    @Test
    public void deleteReview()
    {
        memberRepository.save(new Member("test1"));
        memberRepository.save(new Member("test2"));

        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();

        Long memId1 = memberRepository.findByname("test1").getId();
        Long memId2 = memberRepository.findByname("test2").getId();

        Long aLong = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"aaa","111"));
        Long aLong1 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"bbb","222"));
        Long aLong2 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId1,"ccc","333"));

        Long aLong3 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId2,"ddd","444"));
        Long aLong4 = reviewService.reviewRegist(new ReviewRegistRequestDto(bookId,memId2,"eee","555"));

        reviewService.deleteReview(aLong2);
        reviewService.deleteReview(aLong4);

        List<Review> all = reviewRepository.findAll();

        assertThat(all.size()).isEqualTo(3);

        PageRequest of = PageRequest.of(0, 3);

        Page<Review> reviewByMemberId = reviewRepository.findReviewByMemberId(memId1, of);
        Page<Review> reviewByMemberId1 = reviewRepository.findReviewByMemberId(memId2, of);

        assertThat(reviewByMemberId.getContent().size()).isEqualTo(2);
        assertThat(reviewByMemberId1.getContent().size()).isEqualTo(1);
    }
}
