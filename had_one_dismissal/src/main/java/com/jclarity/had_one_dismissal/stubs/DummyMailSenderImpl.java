package com.jclarity.had_one_dismissal.stubs;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSenderImpl implements MailSender {

    @Override
    public void send(SimpleMailMessage message) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage[] message) throws MailException {

    }

}
