package com.CryptoTracker.CryptoTracker;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class MockMailSender implements JavaMailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        System.out.println(">>> MOCK EMAIL <<<");
        System.out.println("To: " + String.join(", ", simpleMessage.getTo()));
        System.out.println("Subject: " + simpleMessage.getSubject());
        System.out.println("Body: " + simpleMessage.getText());
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) { }


    @Override
    public MimeMessage createMimeMessage() {
        return new MimeMessage((jakarta.mail.Session) null);
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        try {
            return new MimeMessage((jakarta.mail.Session) null, contentStream);
        } catch (Exception e) {
            throw new MailException("Failed to create MimeMessage", e) {};
        }
    }
    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {
        System.out.println(">>> MOCK EMAIL single MimeMessage <<<");
    }
}
