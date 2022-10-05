package com.winvents.email.application.service;

import com.winvents.email.application.entity.Email;
import com.winvents.email.application.entity.enums.EmailStatus;
import com.winvents.email.application.infrastructure.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Email sendEmail(Email emailModel) {
        emailModel.setDateSent(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e){
            emailModel.setEmailStatus(EmailStatus.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public List<Email> listEmails() {
        return emailRepository.findAll();
    }
}
