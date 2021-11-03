package com.springboot.st.signupProject.web.dto;

import com.springboot.st.domain.posts.Posts;
import com.springboot.st.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserFormDto {

    @Autowired
    PasswordEncoder passwordEncoder;

    @NotBlank(message = "nameは必須入力値です。")
    private String username;

    @NotBlank(message = "emailは必須入力値です。")
    @Email(message = "email 形式で入力をお願いします。")
    private String email;

    @NotBlank(message = "passwordは必須入力値です。")
    @Length(min = 6,max = 16, message = "passwordは 6文字以上　16文字以下で入力をお願いします。")
    private String password;

    //Posts의 DB Layer를 생성자로 생성하는 메소드 View단에서 사용하고 직접 DB와는 접근하지 않음
    public User toEntity(){
        String encode_password = passwordEncoder.encode(password);
        return User.builder().name(username).email(email).password(encode_password).build();
    }

}
