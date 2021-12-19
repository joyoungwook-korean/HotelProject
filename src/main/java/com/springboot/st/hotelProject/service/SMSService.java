package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import lombok.*;
import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Getter
@Service
@RequiredArgsConstructor
public class SMSService {

    @Value("${sms.key}")
    private String key;

    @Value("${sms.secretkey}")
    private String secretKey;

    @Value("${sms.phonenum}")
    private String phoneNum;


    public void sms_Send(){
        Message message = new Message(key,secretKey);
        System.out.println(key);
        System.out.println(phoneNum);
        System.out.println(secretKey);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("to",phoneNum);
        hashMap.put("from",phoneNum);
        hashMap.put("type","SMS");

        hashMap.put("text","vv");
        hashMap.put("app_version","test app 1.2");

        try{
            System.out.println("try in");
            JSONObject obj = (JSONObject) message.send(hashMap);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println("error-----------------");
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            System.out.println("---------------");
        }

    }
    //sms_send logic
    public void sms_Send(Hotel_Reservation hotel_reservation){
        System.out.println("inin");
        Message message = new Message(key,secretKey);
        System.out.println(key);
        System.out.println(phoneNum);
        System.out.println(secretKey);
        System.out.println(hotel_reservation.getPhoneNum());


        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("to",hotel_reservation.getPhoneNum());
        hashMap.put("from",phoneNum);
        hashMap.put("type","SMS");

        String name = null;
        if(hotel_reservation.getUser()!=null){
            name = hotel_reservation.getUser().getName();
        }else{
            name = hotel_reservation.getAnotherUser();
        }

        String text_send = "Name : "+ name +"\n"+
                "Num : " +hotel_reservation.getId()+"\n"+
                "CheckIn : " + hotel_reservation.getStartDay()+"\n"+
                "CheckOut : " + hotel_reservation.getFinishDay()+"\n"+
                "People : " + hotel_reservation.getPeople()+"\n"+
                "Pee : "+"$ " +hotel_reservation.getPayment().getPayPrice();
//        hashMap.put("image","https://rbwsn-s3-image.s3.ap-northeast-2.amazonaws.com/710b6ad1-dbda-438f-af5d-86db8151b0a5executive_hotel5.jpg");
        hashMap.put("text",text_send);
        hashMap.put("app_version","test app 1.2");

        try{
            System.out.println("try in");
            JSONObject obj = (JSONObject) message.send(hashMap);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println("error-----------------");
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            System.out.println("---------------");
        }


    }

}
