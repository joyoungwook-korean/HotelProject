package com.springboot.st.config.auth;

import com.springboot.st.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console화면을 이용하기 위한 disable
        http.csrf().disable().headers().frameOptions().disable().
                and()
                .authorizeRequests() // URL별 권한 관리 설정 시작점
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**","/profile").permitAll();

    }
}
