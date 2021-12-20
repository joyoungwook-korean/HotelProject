package com.springboot.st.hotelProject.domain.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class MailDto {

    private String email;
    private String subject;
    private String message;
    private MultipartFile file;
}