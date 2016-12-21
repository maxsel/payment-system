package com.tofi.shop.service.impl.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements com.tofi.shop.service.MailService{
    private final String Subject = "Your order code";

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendCode(String mail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setSubject(Subject);
        message.setText(code);
        message.setFrom("kirill2max@gmail.com");
        mailSender.send(message);
    }
}
