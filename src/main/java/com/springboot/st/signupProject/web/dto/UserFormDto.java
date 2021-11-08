package com.springboot.st.signupProject.web.dto;


import com.springboot.st.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class UserFormDto {

    @NotBlank(message = "idは必修入力です")
    @Length(min = 6,max = 16, message = "idは 6文字以上　16文字以下で入力をお願いします。")
    private String userid;


    @NotBlank(message = "nameは必須入力です。")
    private String username;

    @NotBlank(message = "emailは必須入力です。")
    @Email(message = "email 形式で入力をお願いします。")
    private String email;

    @NotBlank(message = "passwordは必須入力です。")
    @Length(min = 6,max = 16, message = "passwordは 6文字以上　16文字以下で入力をお願いします。")
    private String password;

    private String provider;

    //Posts의 DB Layer를 생성자로 생성하는 메소드 View단에서 사용하고 직접 DB와는 접근하지 않음
    public User toEntity(){
        if(provider==null){
            return User.builder().userid(userid).name(username).email(email).provider("local").password(password).build();
            }else{
            return User.builder().userid(userid).name(username).email(email).provider(provider).password(password).build();

        }
    }

    @Builder
    public UserFormDto(String userid, String username, String email, String password, String provider) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.provider = provider;
    }
}
