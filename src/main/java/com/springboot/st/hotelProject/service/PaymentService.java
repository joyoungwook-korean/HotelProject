package com.springboot.st.hotelProject.service;

import com.springboot.st.domain.pay.Payment;
import com.springboot.st.domain.pay.PaymentRepository;
import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    public Payment save(Map<String,Object> receipt){

        Map<String,Object> params = (Map<String, Object>) receipt.get("params");
        Payment payment =null;

        System.out.println(params.toString());
        System.out.println(receipt.toString());

        if(params.get("another_user")!=null){
            System.out.println("not null");

            payment = new Payment().builder()
                    .receiptId((String)receipt.get("receipt_id"))
                    .methondName((String)receipt.get("method_name"))
                    .payPrice((String) params.get("pee"))
                    .roomName((String)params.get("room"))
                    .anotherUser((String) params.get("another_user"))
                    .phoneNum((String) params.get("phone"))
                    .payTid((String) receipt.get("private_key"))
                    .payUser(null)
                    .payContent((String)receipt.get("receipt_url"))
                    .build();

        }else{
            Map<String,Object> get_User_Map = (Map<String, Object>) params.get("user");
            System.out.println((int)get_User_Map.get("id"));
            Long longaa =  new Long((int)get_User_Map.get("id"));
            User user = userRepository.findById(longaa)
                    .orElseThrow(NullPointerException::new);

            payment = new Payment().builder()
                    .receiptId((String)receipt.get("receipt_id"))
                    .methondName((String)receipt.get("method_name"))
                    .payPrice((String) params.get("pee"))
                    .roomName((String)params.get("room"))
                    .anotherUser(null)
                    .phoneNum((String) params.get("phone"))
                    .payTid((String) receipt.get("private_key"))
                    .payUser(user)
                    .payContent((String)receipt.get("receipt_url"))
                    .build();
        }

        System.out.println(payment.getPhoneNum());
        Payment payment1=paymentRepository.save(payment);

        return payment1;
    }


}
