package com.springboot.st.config.oauth2;


import com.springboot.st.config.auth.PrincipalDetails;
import com.springboot.st.config.oauth2.provider.GoogleOAuth2Provider;
import com.springboot.st.config.oauth2.provider.KakaoOAuth2UserInfo;
import com.springboot.st.config.oauth2.provider.NaverOAuth2Provider;
import com.springboot.st.config.oauth2.provider.OAuth2UserInfo;
import com.springboot.st.domain.user.Role;
import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;

import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {




    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = null;
        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleOAuth2Provider(oAuth2User.getAttributes());
        }else if(provider.equals("naver")){
            System.out.println("asd");
            oAuth2UserInfo = new NaverOAuth2Provider((Map) oAuth2User.getAttributes().get("response"));

        }else if(provider.equals("kakao")){
            System.out.println(oAuth2User.getAttributes());
            oAuth2UserInfo = new KakaoOAuth2UserInfo(oAuth2User.getAttributes(),(Map) oAuth2User.getAttributes().get("properties"),
                    (Map) oAuth2User.getAttributes().get("kakao_account"));

        }

        String providerId = oAuth2UserInfo.getProviderId();
        String userid = provider + "_" + providerId;
        String email = oAuth2UserInfo.getEmail();
        String username = oAuth2UserInfo.getName();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("RBWSNboot");


        User user = userRepository.findByUserid(userid);

        if(user ==null){
            UserFormDto userFormDto = UserFormDto.builder()
                    .userid(userid)
                    .username(username)
                    .email(email)
                    .provider(provider)
                    .password(password)
                    .build();
            user = userFormDto.toEntity();
            userRepository.save(user);
        }

        return  new PrincipalDetails(user,oAuth2User.getAttributes());

    }
}
