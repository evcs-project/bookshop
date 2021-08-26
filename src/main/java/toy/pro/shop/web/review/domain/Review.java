package toy.pro.shop.web.review.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private Review(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public  static Review createReview(String title, String content) {
        return new Review(title, content);
    }

    public void updateReview(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void setMember(Member member)
    {
        member.getReviewList().add(this);
        this.member = member;
    }

    public void setBook(Book book) {
        book.getReviewList().add(this);
        this.book = book;
    }
}
