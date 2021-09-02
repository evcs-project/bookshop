package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.cart.dto.RequestDto.CartGetRequestDto;
import toy.pro.shop.web.cart.dto.RequestDto.CartRegistRequestDto;
import toy.pro.shop.web.cart.dto.ResponseDto.CartResponseDto;
import toy.pro.shop.web.cart.dto.RequestDto.CartUpdateRequestDto;
import toy.pro.shop.web.cart.service.CartService;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@Api(tags = "장바구니 관련 api")
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @ApiOperation("장바구니에 담기")
    @PostMapping
    public void addCart(@RequestBody @Valid CartRegistRequestDto cartRegistRequestDto)
    {
        cartService.registCart(cartRegistRequestDto);
    }


    @ApiOperation("나의 장바구니 가져오기")
    @GetMapping("/mycart/{id}")
    public CartResponseDto getMyCart(@Valid CartGetRequestDto cartGetRequestDto)
    {
        return cartService.getMycartlist(cartGetRequestDto);
    }


    @ApiOperation("장바구니에서 삭제하기")
    @DeleteMapping("/delete/{cartid}")
    public void deleteCart(@PathVariable(value = "cartid") Long id)
    {
        cartService.deleteCartById(id);
    }


    @ApiOperation("장바구니에 담은 수량 수정하기")
    @PutMapping("/update/{cartid}")
    public void updateCart(@PathVariable(value = "cartid") Long id
            , @RequestBody @Valid CartUpdateRequestDto cartUpdateRequestDto)
    {
        cartService.UpdteCart(cartUpdateRequestDto);
    }


}
