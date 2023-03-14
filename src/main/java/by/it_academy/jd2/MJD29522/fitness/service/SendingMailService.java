package by.it_academy.jd2.MJD29522.fitness.service;

import by.it_academy.jd2.MJD29522.fitness.service.api.ISendingMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendingMailService implements ISendingMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;
    @Override
    public void send(String mailRecipient, String subject, String message) {
        SimpleMailMessage sendingMessage = new SimpleMailMessage();
        sendingMessage.setFrom(username);
        sendingMessage.setTo(mailRecipient);
        sendingMessage.setSubject(subject);
        sendingMessage.setText(message);
        mailSender.send(sendingMessage);
    }
}
