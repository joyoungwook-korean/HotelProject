package com.springboot.st.config;

import com.springboot.st.config.oauth2.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console화면을 이용하기 위한 disable
        http.csrf().disable().headers().frameOptions().disable().
                and()
                .authorizeRequests() // URL별 권한 관리 설정 시작점
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
                 .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**","/profile","/**","/signup/**").permitAll()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("userid")
                .passwordParameter("password")
                .and()
                .oauth2Login()
                .loginPage("/")
        .userInfoEndpoint()
        .userService(principalOauth2UserService)
                ;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
