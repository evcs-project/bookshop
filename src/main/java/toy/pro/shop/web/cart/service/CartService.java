package toy.pro.shop.web.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.pro.shop.web.book.domain.Book;
import toy.pro.shop.web.book.domain.BookRepository;
import toy.pro.shop.web.cart.domain.Cart;
import toy.pro.shop.web.cart.domain.CartRepository;
import toy.pro.shop.web.cart.dto.ResponseDto.CartDto;
import toy.pro.shop.web.cart.dto.RequestDto.CartRegistRequestDto;
import toy.pro.shop.web.cart.dto.ResponseDto.CartResponseDto;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public CartResponseDto getMycartlist(Long memberid, Pageable pageable){
        Page<Cart> cartByMemberId = cartRepository.findCartByMemberId(memberid, pageable);

        List<CartDto> collect = cartByMemberId.getContent()
                .stream().map(CartDto::toCartDto)
                .collect(Collectors.toList());
        if(collect.isEmpty()){
            throw new GlobalApiException(ErrorCode.DATA_NOT_FOUND);
        }
        return CartResponseDto
                .builder()
                .cartDtoList(collect).build();

    }

    @Transactional
    public void deleteCartById(Long cartid){
        cartRepository.deleteById(cartid);
    }

    @Transactional
    public Long registCart(CartRegistRequestDto cartRegistRequestDto){

        Book book = bookRepository.findById(cartRegistRequestDto.getBookid())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));
        Member member = memberRepository.findById(cartRegistRequestDto.getMemberid())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.USER_NOT_FOUND));
        return cartRepository.save(new Cart(cartRegistRequestDto.getCount(),book,member)).getCartId();
    }

    @Transactional
    public Long UpdteCart(Long cartid,int count){
        return cartRepository.updateCartcount(cartid, count);
    }

}
