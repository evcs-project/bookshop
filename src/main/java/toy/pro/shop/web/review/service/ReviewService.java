package toy.pro.shop.web.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;
import toy.pro.shop.web.review.domain.Review;
import toy.pro.shop.web.review.domain.ReviewRepository;
import toy.pro.shop.web.review.dto.RequestDto.ReviewRegistRequestDto;
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
    public Long reviewRegist(ReviewRegistRequestDto reviewRegistRequestDto)
    {
        Book book = bookRepository.findById(reviewRegistRequestDto.getBookId())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));

        Member member = memberRepository.findById(reviewRegistRequestDto.getMemberId())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.USER_NOT_FOUND));

        Review review = reviewRegistRequestDto.toEntity();
        review.setBook(book);
        review.setMember(member);
        reviewRepository.save(review);
        return review.getReviewId();
    }

    @Transactional
    public void reviewUpdate(Long id, ReviewUpdateRequestDto reviewUpdateRequestDto)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        review.reviewUpdate(reviewUpdateRequestDto.getTitle(),reviewUpdateRequestDto.getContent());
    }

    @Transactional
    public void deleteReview(Long id)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));

        reviewRepository.delete(review);
    }

    public ReviewResponseDto findById(Long id)
    {
        Review entity = reviewRepository.findById(id)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        return new ReviewResponseDto(entity);
    }

}
