package com.likelion.picpic.configuration;

import com.likelion.picpic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  //지정한 기능에 인증 강요
@RequiredArgsConstructor
public class AuthenticationConfig{
    private final UserService userService;
    @Value("&{jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(
                        "/user/join",
                        "/user/login",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/swagger-resources/**"
                ).permitAll()  // 허용
                //.antMatchers(HttpMethod.POST,"/review/**").authenticated() //비허용
                .anyRequest().authenticated()  //회원가입, 로그인 제외 모든 요청에 인증 요구
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt사용
                .and()
                .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                //받은 토큰을 디코딩하기 위하여 JwtFilter가 선처리
                .build();
    }
}
