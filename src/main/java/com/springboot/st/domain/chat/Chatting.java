package com.springboot.st.domain.chat;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.user.Role;
import com.springboot.st.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Chatting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User chat_user;

    private String message;

    @Builder
    public Chatting( User chat_user, String message) {
        this.chat_user = chat_user;
        this.message = message;
    }
}
