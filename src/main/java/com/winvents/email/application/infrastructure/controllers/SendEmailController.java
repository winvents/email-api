package com.winvents.email.application.infrastructure.controllers;

import com.winvents.email.application.entity.Email;
import com.winvents.email.application.entity.dtos.EmailDto;
import com.winvents.email.application.service.SendEmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("email")
public class SendEmailController {

    @Autowired
    SendEmailService sendEmailService;

    @PostMapping
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        Email emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);
        sendEmailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
