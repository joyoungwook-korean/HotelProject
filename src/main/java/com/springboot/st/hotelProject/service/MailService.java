package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMailHelper(MailDto mail) throws Exception {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom("Rolax Resort <admin@rolax.com>");
            helper.setTo(mail.getEmail());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getMessage());
            if (mail.getFile().getName().equals("attachment")) {
                mailSender.send(message);
            } else {
                helper.addAttachment(mail.getFile().getOriginalFilename(), mail.getFile());
                mailSender.send(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}