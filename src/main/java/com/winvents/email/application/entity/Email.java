package com.winvents.email.application.entity;

import com.winvents.email.application.entity.enums.EmailStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long emailId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(length = 10000)
    private String text;
    private LocalDateTime dateSent;
    private EmailStatus emailStatus;

}
