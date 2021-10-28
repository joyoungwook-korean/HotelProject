package com.springboot.st.config.auth;

import com.springboot.st.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console화면을 이용하기 위한 disable
        http.csrf().disable().headers().frameOptions().disable().
                and()
                .authorizeRequests() // URL별 권한 관리 설정 시작점
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**","/profile").permitAll().
                //권한 관리 대상을 지정하는 옵션 / 등 지정된 URL은 permitALL옵션을 통해 전체 열람권한
                        antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //USER(일반사용자)의 권한만 있는 사람에게 api/v1/**의 권한을 줌
                .anyRequest().//설정된 값들 이외 나머지 url을 나타냄
                authenticated(). // 로그인 사용자(인증된 사용자)들에게만 허용
                        and()
                .logout().logoutSuccessUrl("/") // 로그아웃 기능
                .and()
                .oauth2Login(). // OAuth2 로그인 기능에 시작점
                userInfoEndpoint(). // 로그인 성공 이후 사용자 정보를 가져올때의 설정
                userService(customOAuth2UserService);
        // 로그인 성공시 후속 조치를 진행할 UserService구현체 등록
        //즉 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능 명시

    }
}
