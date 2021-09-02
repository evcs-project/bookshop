package toy.pro.shop.web.member.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import toy.pro.shop.security.CustomUserDetailsService;
import toy.pro.shop.web.exception.ErrorCode;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @SuppressWarnings("unchecked")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if(!matchPassword(password, user.getPassword()))
        {
            log.error("matchPassword error");
            throw new BadCredentialsException(ErrorCode.PASSWORD_NOT_MATCHED.getName());
        }

        if(!user.isEnabled())
        {
            throw new BadCredentialsException(username);
        }

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }

    private boolean matchPassword(String loginPwd, String password)
    {
        return passwordEncoder.matches(loginPwd, password);
    }

}