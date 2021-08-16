//package toy.pro.shop.web.cart.domain;
//
//import lombok.Getter;
//import toy.pro.shop.web.book.domain.Book;
//import toy.pro.shop.web.member.domain.Member;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "cart_id")
//    private Long cartId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "book_id")
//    private Book book;
//
//}
