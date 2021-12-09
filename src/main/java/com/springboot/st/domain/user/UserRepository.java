package com.springboot.st.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //email로 로그인 하는 계정중 이미 생성된 사용자 인지 처음 가입하는지 판단
    Optional<User> findByEmail(String email);

    List<User> findByEmailContaining(String find);

    User findByUserid(String userid);

}
