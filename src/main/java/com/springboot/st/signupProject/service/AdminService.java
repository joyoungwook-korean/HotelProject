package com.springboot.st.signupProject.service;

import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public List<User> find_user_ajax(String find){
        List<User> users = userRepository.findByEmailContaining(find);
        return users;
    }


}
