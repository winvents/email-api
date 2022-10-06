package com.winvents.email.application.infrastructure.controllers;

import com.winvents.email.application.entity.Email;
import com.winvents.email.application.entity.dtos.EmailDto;
import com.winvents.email.application.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("email")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        Email emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Email>> listSentEmails() {
        List<Email> sentEmails = emailService.listEmails();
        return new ResponseEntity<>(sentEmails, HttpStatus.OK);
    }

}
