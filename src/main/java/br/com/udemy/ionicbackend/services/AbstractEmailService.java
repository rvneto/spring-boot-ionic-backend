package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage msg = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(msg);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(pedido.getCliente().getEmail());
        mail.setFrom(sender);
        mail.setSubject("Pedido confirmado! Código: " + pedido.getId());
        mail.setSentDate(new Date((System.currentTimeMillis())));
        mail.setText(pedido.toString());
        return mail;
    }
}
