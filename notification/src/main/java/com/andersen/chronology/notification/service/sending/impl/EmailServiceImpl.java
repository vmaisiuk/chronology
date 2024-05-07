package com.andersen.chronology.notification.service.sending.impl;

import com.andersen.chronology.notification.properties.EmailProperty;
import com.andersen.chronology.notification.service.sending.EmailService;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailProperty emailProperty;

    @Override
    public NotificationSendResponse send(NotificationSendRequest notification) {
        NotificationSendResponse response = new NotificationSendResponse();
        response.setNotificationId(notification.getNotificationId());
        try {
            javaMailSender.send(getMessage(notification));
            log.debug("Sent email notification with id {}", notification.getNotificationId());
            return response;
        } catch (MailParseException | MailAuthenticationException | MailSendException |
                 MessagingException exception) {
            log.error(exception.getMessage());
            response.setErrorMessage(exception.getMessage());
            return response;
        }
    }

    private MimeMessage getMessage(NotificationSendRequest request) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        helper.setFrom(emailProperty.getUserName());
        helper.setTo(request.getSendTo());
        helper.setText(request.getContent(), true);
        helper.setSubject(request.getTitle());

        return mimeMessage;
    }
}
