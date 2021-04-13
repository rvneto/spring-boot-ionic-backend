package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

}
