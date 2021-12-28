package com.springboot.st.hotelProject.domain.dto;

import com.springboot.st.domain.pay.Payment;
import com.springboot.st.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter @Setter
public class PaymentDto {

    private Long id;

    private User payUser;

    private String anotherUser;

    private String roomName;

    private String phoneNum;

    private String payContent;

    private String receiptId;

    private String methondName;

    private String payPrice;

    private String payTid;

    private static ModelMapper modelMapper = new ModelMapper();

    public static PaymentDto of(Payment payment) {
        return modelMapper.map(payment, PaymentDto.class);
    }
}
