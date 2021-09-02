package toy.pro.shop.web.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.auth.AuthUtil;
import toy.pro.shop.web.member.domain.*;
import toy.pro.shop.web.member.dto.MemberChangePasswordDto;
import toy.pro.shop.web.member.dto.MemberSignUpRequestDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Optional<Member> findByEmail(String email)
    {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public void signUp(MemberSignUpRequestDto requestDto)
    {
        checkDuplicateMember(requestDto.getEmail());
        List<Role> roles = roleRepository.findByNames(requestDto.getRoleName());
        Member member = Member.newMember(
                passwordEncoder.encode(requestDto.getPassword())
                , requestDto.getEmail()
                , requestDto.getName()
                , roles
        );
        memberRepository.save(member);
    }

    public void deleteMember()
    {
        Member member = memberRepository.findByEmail(AuthUtil.getCurUserEmail())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.DATA_NOT_FOUND));

        memberRepository.delete(member);
    }

    @Transactional
    public void changePassword(MemberChangePasswordDto requestDto)
    {
        Member member = memberRepository.findByEmail(AuthUtil.getCurUserEmail())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.USER_NOT_FOUND));

        String encodedNewPassword = passwordEncoder.encode(requestDto.getPassword());

        if (passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
        {
            throw new GlobalApiException(ErrorCode.PASSWORD_EQUAL_ERROR);
        }

        member.changePassword(member.getPassword(), encodedNewPassword);
    }

    public void checkDuplicateMember(String email)
    {
        Optional<Member> memberByNickname = memberRepository.findByEmail(email);

        if (memberByNickname.isPresent())
        {
            throw new GlobalApiException(ErrorCode.USER_DUPLICATION);
        }
    }
}
