package com.springboot.st.signupProject.service;

import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(UserFormDto userFormDto) throws Exception{
        User user = userFormDto.toEntity();
        return userRepository.save(user).getId();
    }


}
