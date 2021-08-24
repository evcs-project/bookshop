package toy.pro.shop.web.cart.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.cart.domain.Cart;
import toy.pro.shop.web.cart.domain.CartRepository;
import toy.pro.shop.web.cart.dto.CartRegistRequestDto;
import toy.pro.shop.web.cart.dto.CartResponseDto;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    static Long bookId;
    static Long bookId1;
    static Long bookId2;
    static Long memid;
    static Long memid1;
    static Long aLong;
    static Long aLong1;
    static Long aLong2;
    static Long aLong3;
    static Long aLong4;

    @BeforeEach
    void init(){
        memberRepository.save(new Member("김시덕"));
        memberRepository.save(new Member("박상혁"));
    }

    @Test
    @DisplayName("유저가 책을 선택해서 개수를 정해서 장바구니에 넣는다")
    @Transactional
    @Rollback(value = true)
    public void cartregistTest(){
        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        bookId = bookSearchResponseDto.getBookId();
        bookId1 = bookSearchResponseDto1.getBookId();
        bookId2 = bookSearchResponseDto2.getBookId();

        memid = memberRepository.findByname("김시덕").getId();
        memid1 = memberRepository.findByname("박상혁").getId();

        aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3, memid));
        aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5, memid));
        aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2, memid1));
        aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7, memid1));
        aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9, memid1));

        PageRequest of = PageRequest.of(0, 3);

        List<Cart> all = cartRepository.findAll();
        assertThat(all.size()).isEqualTo(5);

        Page<Cart> cartByMemberId = cartRepository.findCartByMemberId(memid, of);
        Page<Cart> cartByMemberId1 = cartRepository.findCartByMemberId(memid1, of);

        assertThat(cartByMemberId.getContent().size()).isEqualTo(2);
        assertThat(cartByMemberId.getContent().get(0).getCount()).isEqualTo(3);
        assertThat(cartByMemberId.getContent().get(1).getCount()).isEqualTo(5);

        assertThat(cartByMemberId1.getContent().size()).isEqualTo(3);
        assertThat(cartByMemberId1.getContent().get(0).getCount()).isEqualTo(2);
        assertThat(cartByMemberId1.getContent().get(1).getCount()).isEqualTo(7);
        assertThat(cartByMemberId1.getContent().get(2).getCount()).isEqualTo(9);

    }

    @Test
    @Transactional
    @DisplayName("장바구니에서 삭제하기")
    public void deletecartTest(){
        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        bookId = bookSearchResponseDto.getBookId();
        bookId1 = bookSearchResponseDto1.getBookId();
        bookId2 = bookSearchResponseDto2.getBookId();

        memid = memberRepository.findByname("김시덕").getId();
        memid1 = memberRepository.findByname("박상혁").getId();

        aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3, memid));
        aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5, memid));
        aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2, memid1));
        aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7, memid1));
        aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9, memid1));

        List<Cart> all = cartRepository.findAll();
        assertThat(all.size()).isEqualTo(5);

        cartService.deleteCartById(aLong);

        List<Cart> all1 = cartRepository.findAll();
        assertThat(all1.size()).isEqualTo(4);

        cartService.deleteCartById(aLong3);

        PageRequest of = PageRequest.of(0, 3);

        Page<Cart> cartByMemberId = cartRepository.findCartByMemberId(memid, of);
        Page<Cart> cartByMemberId1 = cartRepository.findCartByMemberId(memid1, of);

        assertThat(cartByMemberId.getContent().size()).isEqualTo(1);
        assertThat(cartByMemberId.getContent().get(0).getCount()).isEqualTo(5);

        assertThat(cartByMemberId1.getContent().size()).isEqualTo(2);
        assertThat(cartByMemberId1.getContent().get(0).getCount()).isEqualTo(2);
        assertThat(cartByMemberId1.getContent().get(1).getCount()).isEqualTo(9);


    }

    @Test
    @DisplayName("멤버의 장바구니 조회하기")
    public void getMyCartTest(){
        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        bookId = bookSearchResponseDto.getBookId();
        bookId1 = bookSearchResponseDto1.getBookId();
        bookId2 = bookSearchResponseDto2.getBookId();

        memid = memberRepository.findByname("김시덕").getId();
        memid1 = memberRepository.findByname("박상혁").getId();

        aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3, memid));
        aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5, memid));
        aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2, memid1));
        aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7, memid1));
        aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9, memid1));

        PageRequest of = PageRequest.of(0, 3);

        List<CartResponseDto> mycartlist = cartService.getMycartlist(memid, of);
        List<CartResponseDto> mycartlist1 = cartService.getMycartlist(memid1, of);

        assertThat(mycartlist.size()).isEqualTo(2);
        assertThat(mycartlist1.size()).isEqualTo(3);

        assertThat(mycartlist.get(0).getCount()).isEqualTo(3);
        assertThat(mycartlist.get(1).getCount()).isEqualTo(5);

        assertThat(mycartlist1.get(0).getCount()).isEqualTo(2);
        assertThat(mycartlist1.get(1).getCount()).isEqualTo(7);
        assertThat(mycartlist1.get(2).getCount()).isEqualTo(9);

    }

    @Test
    @DisplayName("카트의 수량을 수정 할 수 있다.")
    @Transactional
    public void updateCartTest(){
        Page<BookSearchResponseDto> spring = bookRepository.search(new BookSearchRequestDto(10, 0, "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        bookId = bookSearchResponseDto.getBookId();
        bookId1 = bookSearchResponseDto1.getBookId();
        bookId2 = bookSearchResponseDto2.getBookId();

        memid = memberRepository.findByname("김시덕").getId();
        memid1 = memberRepository.findByname("박상혁").getId();

        aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3, memid));
        aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5, memid));
        aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2, memid1));
        aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7, memid1));
        aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9, memid1));

        PageRequest of = PageRequest.of(0, 3);

        cartService.UpdteCart(aLong,8);
        cartService.UpdteCart(aLong3,15);

        List<CartResponseDto> mycartlist = cartService.getMycartlist(memid, of);
        List<CartResponseDto> mycartlist1 = cartService.getMycartlist(memid1, of);

        assertThat(mycartlist.get(0).getCount()).isEqualTo(8);
        assertThat(mycartlist1.get(1).getCount()).isEqualTo(15);
    }

}