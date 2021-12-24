package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import lombok.*;
import net.nurigo.java_sdk.api.Image;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

    @Value("${naver.service.id}")
    private String naverAccessKey;

    @Value("${naver.service.secret}")
    private String naverAccessSecretKey;

    @Value("${naver.sms.name}")
    private String naverServiceId;

    @Value("${naver.sms.secret}")
    private String naverSmsSecretKey;


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

    public void naverSmsSendService(Hotel_Reservation hotel_reservation){
        String host = "https://sens.apigw.ntruss.com";
        String requestUrl = "/sms/v2/services/";
        String serviceId = naverServiceId;
        String accessKey = naverAccessKey;
        String accessSecretKey = naverAccessSecretKey;
        String secretKey = naverSmsSecretKey;
        String method = "POST";
        String timestamp = Long.toString(System.currentTimeMillis());
        requestUrl +=serviceId + "/messages";
        String urlFull = host + requestUrl;

        JSONObject naverJSON = new JSONObject();
        JSONObject messages = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONObject file = new JSONObject();
        try {
            naverJSON.put("type","SMS");
            naverJSON.put("contentType","COMM");
            naverJSON.put("from","01023364961");
            naverJSON.put("countryCode","82");

            messages.put("to",hotel_reservation.getPhoneNum());
//            messages.put("subject",
//                    hotel_reservation.getReHotelRoom().getRoomName() +"의 예약 정보");
            messages.put("content","잘 부탁드립니다.");


            messagesArray.add(messages);
            naverJSON.put("messages", messagesArray);

        }catch (Exception e){
            e.getStackTrace();
        }

        System.out.println(urlFull);
        System.out.println(naverJSON);

        URL url = null;
        try {
            url = new URL(urlFull);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type","application/json; charset=utf-8");
            con.setRequestProperty("x-ncp-apigw-timestamp",timestamp);
            con.setRequestProperty("x-ncp-iam-access-key",accessKey);

            String space = " ";					// one space
            String newLine = "\n";					// new line

            String me = new StringBuilder()
                    .append(method)
                    .append(space)
                    .append(requestUrl)
                    .append(newLine)
                    .append(timestamp)
                    .append(newLine)
                    .append(accessKey)
                    .toString();

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(accessSecretKey.getBytes("UTF-8"),"HmacSHA256"));

            byte[] hash = mac.doFinal(me.getBytes("UTF-8"));
            String encode = Base64.getEncoder().encodeToString(hash);

            con.setRequestProperty("x-ncp-apigw-signature-v2", encode);
            con.setRequestMethod("POST");
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.write(naverJSON.toString().getBytes());
            wr.flush();
            wr.close();

            int recode = con.getResponseCode();
            BufferedReader br ;
            System.out.println(recode);
            if(recode==202){
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }else{
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputline;
            StringBuffer response = new StringBuffer();
            while ((inputline = br.readLine())!=null){
                response.append(inputline);
            }
            br.close();
            System.out.println(response.toString());




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


    }

}
