package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.web.member.dto.MemberChangePasswordDto;
import toy.pro.shop.web.member.dto.MemberSignUpRequestDto;
import toy.pro.shop.web.member.service.MemberService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Api(tags = "멤버 컨트롤러")
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "회원가입", notes = "회원 가입")
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid MemberSignUpRequestDto requestDto)
    {
        memberService.signUp(requestDto);
    }

    @ApiOperation(value = "회원 비밀번호 변경", notes = "토큰을 가진 유저가 자신의 비밀번호를 변경")
    @PutMapping("/password")
    public void changePassword(@RequestBody MemberChangePasswordDto requestDto)
    {
        memberService.changePassword(requestDto);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "본인 탈퇴 기능입니다.")
    @DeleteMapping("/withdraw")
    public void deleteMember()
    {
        memberService.deleteMember();
    }

}
