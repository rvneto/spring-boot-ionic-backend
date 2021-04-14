package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage msg = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(msg);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        MimeMessage msg = null;
        try {
            msg = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(msg);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mail, true);
        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado! Código: " + pedido.getId());
        mmh.setSentDate(new Date((System.currentTimeMillis())));
        mmh.setText(htmlFromTemplatePedido(pedido), true);
        return mail;
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

    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }
}
