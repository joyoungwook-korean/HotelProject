package com.springboot.st.domain.pay;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.user.User;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User pay_User;

    private String phone_Num;

    private String pay_content;

    @Builder
    public Payment(User pay_User, String phone_Num, String pay_content) {
        this.pay_User = pay_User;
        this.phone_Num = phone_Num;
        this.pay_content = pay_content;
    }

}
