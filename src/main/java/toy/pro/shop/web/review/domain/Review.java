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
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private Review(String content, String title)
    {
        this.content = content;
        this.title=title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public static Review creteReview(String content,String title)
    {
        return new Review(content,title);
    }

    public void updateReview(String title,String content){
        this.title=title;
        this.content=content;
    }

    public void setMember(Member member)
    {
        member.getReviewList().add(this);
        this.member = member;
    }

    public void setBook(Book book)
    {
        book.getReviewList().add(this);
        this.book=book;
    }
}
