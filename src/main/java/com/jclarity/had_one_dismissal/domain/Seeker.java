package com.jclarity.had_one_dismissal.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Seeker {

    @NotNull
    @Size(min = 10)
    private String firstName;

    @NotNull
    @Size(min = 10)
    private String surName;

    private int yearsExperience;

    @Autowired
    private transient MailSender email;

    // TODO: re-add once I've worked out how to avoid spring shitting itself.

    //    @RooUploadedFile(contentType = "application/pdf")
    //    @Lob
    //    private byte[] cv;

    public void sendMessage(String mailFrom, String subject, String mailTo, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setSubject(subject);
        mailMessage.setTo(mailTo);
        mailMessage.setText(message);
        email.send(mailMessage);
    }
}
