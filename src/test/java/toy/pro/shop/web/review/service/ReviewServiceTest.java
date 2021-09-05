package toy.pro.shop.web.review.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;
import toy.pro.shop.web.review.domain.Review;
import toy.pro.shop.web.review.domain.ReviewRepository;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegisterRequestDto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;


@SpringBootTest
@WithMockUser(username = "Test1")
public class ReviewServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @BeforeEach
    void init(){
        memberRepository.save(Member.newMember("1234","Test1","Test",null));
    }

    @Test
    @DisplayName("리뷰 등록 테스트")
    @Transactional
    public void ReviewRegisterTest()
    {
        Page<BookSearchResponseDto> spring = bookRepository.search(
                new BookSearchRequestDto(
                        10,
                        0,
                        "스프링",
                        null,
                        null,
                        null,
                        null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();

        Long aLong = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId,"aaa","111"));
        Long aLong1 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId1,"bbb","222"));
        Long aLong2 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId2,"ccc","333"));
        Long aLong3 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId,"ddd","444"));
        Long aLong4 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId1,"eee","555"));

        PageRequest of = PageRequest.of(0, 3);

        List<Review> all = reviewRepository.findAll();

        assertThat(all.size()).isEqualTo(8);

        Page<Review> reviewByMemberId = reviewRepository.findReviewByMemberEmail("Test1",of);

        assertThat(reviewByMemberId.getTotalElements()).isEqualTo(5);
        assertThat(reviewByMemberId.getContent().size()).isEqualTo(3);
        assertThat(reviewByMemberId.getTotalPages()).isEqualTo(2);
        assertThat(reviewByMemberId.getContent().get(0).getTitle()).isEqualTo("aaa");
        assertThat(reviewByMemberId.getContent().get(1).getTitle()).isEqualTo("bbb");
        assertThat(reviewByMemberId.getContent().get(2).getTitle()).isEqualTo("ccc");
    }

//    @DisplayName("리뷰 수정하기")
//    @Test
//    @Transactional
//    public void ReviewUpdate()
//    {
//        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
//        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
//        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
//        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);
//
//        Long bookId = bookSearchResponseDto.getBookId();
//        Long bookId1 = bookSearchResponseDto1.getBookId();
//        Long bookId2 = bookSearchResponseDto2.getBookId();
//
//        Long aLong = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId,"aaa","111"));
//        Long aLong1 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId1,"bbb","222"));
//        Long aLong2 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId2,"ccc","333"));
//        Long aLong3 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId,"ddd","444"));
//        Long aLong4 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId1,"eee","555"));
//
//        reviewService.reviewUpdate(aLong, ReviewUpdateRequestDto.builder().title("new").content("new1").build());
//
//        Review review = reviewRepository.findById(aLong).get();
//        assertThat(review.getTitle()).isEqualTo("new");
//        assertThat(review.getContent()).isEqualTo("new1");
//    }

//    @DisplayName("리뷰 삭제")
//    @Test
//    public void deleteReview()
//    {
//        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
//        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
//        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
//        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);
//
//        Long bookId = bookSearchResponseDto.getBookId();
//        Long bookId1 = bookSearchResponseDto1.getBookId();
//        Long bookId2 = bookSearchResponseDto2.getBookId();
//
//        Long aLong = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId,"aaa","111"));
//        Long aLong1 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId1,"bbb","222"));
//        Long aLong2 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId2,"ccc","333"));
//        Long aLong3 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId,"ddd","444"));
//        Long aLong4 = reviewService.reviewRegister(new ReviewRegisterRequestDto(bookId1,"eee","555"));
//
//        reviewService.deleteReview(aLong2);
//        reviewService.deleteReview(aLong4);
//
//        List<Review> all = reviewRepository.findAll();
//
//        assertThat(all.size()).isEqualTo(3);
//
//        PageRequest of = PageRequest.of(0, 3);
//
//        Page<Review> reviewByMemberId = reviewRepository.findReviewByMemberId(memId1, of);
//        Page<Review> reviewByMemberId1 = reviewRepository.findReviewByMemberId(memId2, of);
//
//        assertThat(reviewByMemberId.getContent().size()).isEqualTo(2);
//        assertThat(reviewByMemberId1.getContent().size()).isEqualTo(1);
//    }
}