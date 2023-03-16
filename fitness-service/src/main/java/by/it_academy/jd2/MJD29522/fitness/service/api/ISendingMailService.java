package by.it_academy.jd2.MJD29522.fitness.service.api;

public interface ISendingMailService {
    void send(String mailRecipient, String subject, String message);
}
