package com.likelion.picpic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // CSRF 설정 비활성화
                .csrf().disable()

                // 모든 요청에 대해 접근 허용
                .authorizeRequests()
                .antMatchers("/**").permitAll()

                // CORS 설정
                .and().cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내부에서 설정한 경우
        config.addAllowedOriginPattern("*"); // 모든 Origin 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
        config.addAllowedHeader("*"); // 모든 HTTP 헤더 허용
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // CORS 설정
    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // JSESSIONID 등의 쿠키를 주고받기 위함
        config.addAllowedOriginPattern("*"); // 모든 Origin 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 (HTTP) 메소드 허용
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
