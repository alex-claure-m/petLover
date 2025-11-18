package com.example.petlover.service.externo.email;

import com.example.petlover.service.externo.email.models.EmailDetailsDTO;
import jakarta.mail.MessagingException;

public interface EmailService {

	void sendEmail(EmailDetailsDTO details) throws MessagingException;


}
