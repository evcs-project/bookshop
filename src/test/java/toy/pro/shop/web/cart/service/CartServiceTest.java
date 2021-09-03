package toy.pro.shop.web.cart.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.book.dto.request.BookSearchRequestDto;
import toy.pro.shop.web.book.dto.response.BookSearchResponseDto;
import toy.pro.shop.web.cart.domain.Cart;
import toy.pro.shop.web.cart.domain.CartRepository;
import toy.pro.shop.web.cart.dto.RequestDto.CartGetRequestDto;
import toy.pro.shop.web.cart.dto.RequestDto.CartRegistRequestDto;
import toy.pro.shop.web.cart.dto.RequestDto.CartUpdateRequestDto;
import toy.pro.shop.web.cart.dto.ResponseDto.CartResponseDto;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@WithMockUser(username = "Test1")
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    @BeforeEach
    void init(){
        memberRepository.save(Member.newMember("1234","Test1","Test",null));
    }


    @Test
    @DisplayName("유저가 책을 선택해서 개수를 정해서 장바구니에 넣는다")
    @Transactional
    public void cartregistTest(){
        Page<BookSearchResponseDto> spring = bookRepository
                .search(new BookSearchRequestDto(10, 0, "스프링",
                        null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();


        Long aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3));
        Long aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5));
        Long aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2));
        Long aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7));
        Long aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9));

        PageRequest of = PageRequest.of(0, 3);

        List<Cart> all = cartRepository.findAll();

        assertThat(all.size()).isEqualTo(5);

        Page<Cart> cartByMemberId = cartRepository.findCartByMemberemail("Test1",of);


        assertThat(cartByMemberId.getTotalElements()).isEqualTo(5);
        assertThat(cartByMemberId.getContent().size()).isEqualTo(3);
        assertThat(cartByMemberId.getTotalPages()).isEqualTo(2);
        assertThat(cartByMemberId.getContent().get(1).getCount()).isEqualTo(5);
        assertThat(cartByMemberId.getContent().get(2).getCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("장바구니에서 삭제하기")
    @Transactional
    public void deletecartTest(){


        Page<BookSearchResponseDto> spring = bookRepository
                .search(new BookSearchRequestDto(10, 0,
                        "스프링", null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();


        Long aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3));
        Long aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5));

        Long aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2));
        Long aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7));
        Long aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9));

        cartService.deleteCartById(aLong);

        List<Cart> all = cartRepository.findAll();

        assertThat(all.size()).isEqualTo(4);

        cartService.deleteCartById(aLong3);


        CartResponseDto mycartlist = cartService
                .getMycartlist(CartGetRequestDto.builder().page(0).size(3).build());

        assertThat(mycartlist.getCartDtoPage().getContent().size()).isEqualTo(3);
        assertThat(mycartlist.getCartDtoPage().getContent().get(0).getCount()).isEqualTo(5);


    }

    @Test
    @DisplayName("멤버의 장바구니 조회하기")
    @Transactional
    public void getMyCartTest(){

        Page<BookSearchResponseDto> spring = bookRepository
                .search(new BookSearchRequestDto(10, 0, "스프링",
                        null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();


        Long aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3));
        Long aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5));
        Long aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2));
        Long aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7));
        Long aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9));


        CartResponseDto mycartlist = cartService.getMycartlist(CartGetRequestDto.builder()
                .size(3).page(0).build());


        assertThat(mycartlist.getCartDtoPage().getTotalPages()).isEqualTo(2);
        assertThat(mycartlist.getCartDtoPage().getTotalElements()).isEqualTo(5);
        assertThat(mycartlist.getCartDtoPage().getContent().size()).isEqualTo(3);

    }

    @Test
    @DisplayName("카트의 수량을 수정 할 수 있다.")
    @Transactional
    public void updateCartTest(){

        Page<BookSearchResponseDto> spring = bookRepository
                .search(new BookSearchRequestDto(10, 0, "스프링",
                        null, null, null, null));
        BookSearchResponseDto bookSearchResponseDto = spring.getContent().get(0);
        BookSearchResponseDto bookSearchResponseDto1 = spring.getContent().get(1);
        BookSearchResponseDto bookSearchResponseDto2 = spring.getContent().get(2);

        Long bookId = bookSearchResponseDto.getBookId();
        Long bookId1 = bookSearchResponseDto1.getBookId();
        Long bookId2 = bookSearchResponseDto2.getBookId();


        Long aLong = cartService.registCart(new CartRegistRequestDto(bookId, 3));
        Long aLong1 = cartService.registCart(new CartRegistRequestDto(bookId1, 5));
        Long aLong2 = cartService.registCart(new CartRegistRequestDto(bookId2, 2));
        Long aLong3 = cartService.registCart(new CartRegistRequestDto(bookId, 7));
        Long aLong4 = cartService.registCart(new CartRegistRequestDto(bookId1, 9));


        cartService.UpdteCart(CartUpdateRequestDto.builder().Cartid(aLong).count(13).build());
        cartService.UpdteCart(CartUpdateRequestDto.builder().Cartid(aLong4).count(12).build());

        CartResponseDto mycartlist = cartService.getMycartlist(CartGetRequestDto.builder()
                .size(3).page(0).build());

        CartResponseDto mycartlist1 = cartService.getMycartlist(CartGetRequestDto.builder()
                .size(3).page(1).build());

        assertThat(mycartlist.getCartDtoPage().getContent().get(0).getCount()).isEqualTo(13);
        assertThat(mycartlist1.getCartDtoPage().getContent().get(1).getCount()).isEqualTo(12);
    }




}