package com.springboot.st.signupProject.service;

import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Long save(UserFormDto userFormDto) throws Exception {

        if (check_id(userFormDto.getUserid()) == null) {
            String encode_password = passwordEncoder.encode(userFormDto.getPassword());
            userFormDto.setPassword(encode_password);
            User user = userFormDto.toEntity();
            return userRepository.save(user).getId();
        } else {
            throw new IllegalStateException("same user");
        }
    }
    public User get_user_for_userid(String userid){
        return userRepository.findByUserid(userid);
    }

    public User find_User_Id(Long id){
        return  userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public String check_id_for_js(String userid){
        if(userRepository.findByUserid(userid) !=null){
            return "1";
        }else{
            return "0";
        }

    }

    private User check_id(String id) {
        User user = userRepository.findByUserid(id);
        return user;
    }




}
