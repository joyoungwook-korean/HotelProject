package com.springboot.st.config.auth;

import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " + username);
        User user = userRepository.findByUserid(username);
        if(user !=null){
            return new PrincipalDetails(user);
        }else{
            return null;
        }
    }
}
