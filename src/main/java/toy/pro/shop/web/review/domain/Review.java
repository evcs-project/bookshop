package toy.pro.shop.web.review.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.member.domain.Member;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "review")
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Builder // 빌더 패턴 클래스 생성
    private Review(Book book, Member member, String title, String content)
    {
        this.book = book;
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public static Review createReview(Book book, Member member, String title, String content)
    {
        return Review.builder()
                .book(book)
                .member(member)
                .title(title)
                .content(content)
                .build();
    }

    public void reviewUpdate(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public void setMember(Member member)
    {
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setBook(Book book)
    {
        this.book = book;
        book.getReviewList().add(this);
    }
}
