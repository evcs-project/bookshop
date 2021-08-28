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


    public Review(String title, String content, Book book, Member member) {
        this.title = title;
        this.content = content;

        this.book = book;
        this.member = member;
    }

    private Review(String content, String title)
    {
        this.content = content;
        this.title=title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    // 생성 메서드
    public static Review creteReview(String content,String title)
    {
        return new Review(content,title);
    }

    public void updateReview(String title,String content){
        this.title=title;
        this.content=content;
    }

    // 연관관계 메서드 - 양방향
    public void setMember(Member member)
    {
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setBook(Book book)
    {
        this.book=book;
        book.getReviewList().add(this);
    }
}
