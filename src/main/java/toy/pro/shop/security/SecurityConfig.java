package toy.pro.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import toy.pro.shop.security.jwt.JwtAccessDeniedHandler;
import toy.pro.shop.security.jwt.JwtAuthenticationEntryPoint;
import toy.pro.shop.security.jwt.JwtSecurityConfig;
import toy.pro.shop.security.jwt.TokenProvider;
import toy.pro.shop.web.member.auth.CustomAuthenticationProvider;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAccessDeniedHandler jwtAccessDeniedHandler,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .disable()
                .cors().and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .authenticationProvider(authenticationProvider())
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/authenticate").permitAll()
                .antMatchers("/api/user/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/api/member/password").authenticated()
                .antMatchers("/api/member/withdraw").authenticated()
                .antMatchers("/api/order/**").authenticated()
                .antMatchers(
                        "/h2-console/**"
                        , "/favicon.ico"
                        , "/error"
                        , "/**").permitAll()
                .and()

                .apply(new JwtSecurityConfig(tokenProvider));
    }

}
