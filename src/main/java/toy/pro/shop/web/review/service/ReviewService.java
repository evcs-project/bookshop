package toy.pro.shop.web.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.auth.AuthUtil;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;
import toy.pro.shop.web.review.domain.Review;
import toy.pro.shop.web.review.domain.ReviewRepository;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegisterRequestDto;
import toy.pro.shop.web.review.dto.RequestDto.ReviewUpdateRequestDto;
import toy.pro.shop.web.review.dto.ResponseDto.ReviewResponseDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long reviewRegister(ReviewRegisterRequestDto requestDto) {
        String curUserEmail = AuthUtil.getCurUserEmail();
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));

        Member member = memberRepository.findByEmail(curUserEmail)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.USER_NOT_FOUND));

        Review review = Review.createReview(book, member, requestDto.getTitle(), requestDto.getContent());
        reviewRepository.save(review);
        return review.getReviewId();
    }

    @Transactional
    public void reviewUpdate(Long reviewId, ReviewUpdateRequestDto reviewUpdateRequestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        review.reviewUpdate(reviewUpdateRequestDto.getTitle(), reviewUpdateRequestDto.getContent());
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));

        reviewRepository.delete(review);
    }

    public ReviewResponseDto findByReviewId(Long reviewId) {
        Review entity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        return new ReviewResponseDto(entity);
    }
}