package toy.pro.shop.web.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import toy.pro.shop.security.jwt.JwtFilter;
import toy.pro.shop.security.jwt.JwtRequest;
import toy.pro.shop.security.jwt.JwtResponse;
import toy.pro.shop.security.jwt.TokenProvider;
import toy.pro.shop.web.exception.ErrorCode;
import toy.pro.shop.web.exception.GlobalApiException;
import toy.pro.shop.web.member.domain.Member;
import toy.pro.shop.web.member.domain.MemberRepository;

import java.util.Set;

@Api(tags = "토큰 관련 컨트롤러")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/token")
public class JwtController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;

    @Autowired
    public JwtController(
            TokenProvider tokenProvider
            , AuthenticationManagerBuilder authenticationManagerBuilder
            , MemberRepository memberRepository)
    {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@Validated @RequestBody JwtRequest jwtRequest)
    {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Member member = memberRepository.findByEmail(jwtRequest.getEmail()).orElseThrow(()-> new GlobalApiException(ErrorCode.USER_NOT_FOUND));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        Set<String> roleSets = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        return new ResponseEntity<>(new JwtResponse(jwtRequest.getEmail(), token, roleSets, member.getId()), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/log")
    public JwtResponse check(Authentication authentication)
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Member member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(
                        ()->  new GlobalApiException(ErrorCode.USER_NOT_FOUND));

        Set<String> roleSets = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        return new JwtResponse(userDetails.getUsername(), roleSets, member.getId());
    }
}
