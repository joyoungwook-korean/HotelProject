package com.springboot.st.domain.user;
//사용자 정보를 담당할 도메인

import com.springboot.st.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column
    private String provider;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String userid,String name, String email, String password,String provider){
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.role = Role.USER;
    }


    public String getRoleKey(){
        return  this.role.getKey();
    }
}
