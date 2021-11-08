package com.springboot.st.config.oauth2;

import com.springboot.st.config.auth.PrincipalDetails;
import com.springboot.st.domain.user.Role;
import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import com.springboot.st.signupProject.service.UserService;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttributes().get("sub").toString();
        String userid = provider + "_" + providerId;
        String email = oAuth2User.getAttributes().get("email").toString();
        String username = oAuth2User.getAttributes().get("name").toString();
        String password = passwordEncoder.encode("google");


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
