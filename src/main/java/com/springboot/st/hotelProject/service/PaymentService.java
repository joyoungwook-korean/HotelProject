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

        if(params.get("another_user")!=null){
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
            User user = userRepository.findById(Long.parseLong((String) get_User_Map.get("id")))
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

        paymentRepository.save(payment);

        return payment;
    }


}
