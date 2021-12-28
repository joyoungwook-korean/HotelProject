package com.springboot.st.hotelProject.controller;


import com.springboot.st.hotelProject.domain.dto.MailDto;
import com.springboot.st.hotelProject.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class MailController {

    @Autowired
    private final MailService mailService;

    //로그인 정보 존재하지 않을 때 메일 전송
    @PostMapping(value = "/hotel/email/send")
    public String sendMail(@RequestParam("message") String message, @RequestParam("name") String name,
                           @RequestParam("email") String email, @RequestParam("subject") String subject,
                           @RequestParam("attachment") MultipartFile multipartFile) throws Exception {

        MailDto mailDto = new MailDto();


        //받는 메일 주소
        String sendEmail = "rlatjsgh1000@naver.com";

        mailDto.setEmail(sendEmail);
        mailDto.setSubject(subject);
        mailDto.setMessage("E-mail: " + email + System.lineSeparator() + "이름: " + name + System.lineSeparator() + "문의사항: " + message);
        mailDto.setFile(multipartFile);
        mailService.sendMailHelper(mailDto);

        return ("redirect:/hotel/index");
    }

    //로그인 정보 존재할 때 메일 전송
    @PostMapping("/hotel/email/sendAuth")
    public String sendMailAuth(@RequestParam("message") String message, @RequestParam("authName") String authName,
                               @RequestParam("authEmail") String authEmail, @RequestParam("subject") String subject,
                               @RequestParam("attachment") MultipartFile multipartFile) throws Exception {

        MailDto mailDto = new MailDto();

        String sendEmail = "rlatjsgh1000@naver.com";

        mailDto.setEmail(sendEmail);
        mailDto.setSubject(subject);
        mailDto.setMessage("E-mail: " + authEmail + System.lineSeparator() + "이름: " + authName + System.lineSeparator() + "문의사항: " + message);
        mailDto.setFile(multipartFile);
        mailService.sendMailHelper(mailDto);

        return ("redirect:/hotel/index");
    }
}