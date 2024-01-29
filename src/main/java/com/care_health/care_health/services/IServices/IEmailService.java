package com.care_health.care_health.services.IServices;

public interface IEmailService {
    void sendEmail(String to, String subject, String text);
}
