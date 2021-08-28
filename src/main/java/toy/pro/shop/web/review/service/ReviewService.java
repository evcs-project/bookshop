package toy.pro.shop.web.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.cart.domain.Cart;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;
import toy.pro.shop.web.review.domain.Review;
import toy.pro.shop.web.review.domain.ReviewRepository;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegistRequestDto;
import toy.pro.shop.web.review.dto.RequestDto.ReviewUpdateRequestDto;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void updateReview(Long id, ReviewUpdateRequestDto reviewUpdateRequestDto)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        review.updateReview(reviewUpdateRequestDto.getTitle(),reviewUpdateRequestDto.getContent());
    }

    @Transactional
    public void deleteReview(Long id)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        reviewRepository.delete(review);
    }

    @Transactional
    public Long registReview(ReviewRegistRequestDto reviewRegistRequestDto)
    {
        Book book = bookRepository.findById(reviewRegistRequestDto.getBookId())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        Member member = memberRepository.findById(reviewRegistRequestDto.getMemberId())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.USER_NOT_FOUND));
        return reviewRepository.save(new Review(reviewRegistRequestDto.getTitle(),reviewRegistRequestDto.getContent(),book,member)).getReviewId();
    }
}

