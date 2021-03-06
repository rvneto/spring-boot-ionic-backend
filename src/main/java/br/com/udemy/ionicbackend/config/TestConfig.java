package br.com.udemy.ionicbackend.config;

import br.com.udemy.ionicbackend.services.DBService;
import br.com.udemy.ionicbackend.services.EmailService;
import br.com.udemy.ionicbackend.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test") // Indica que é uma configuração especifica para o profile de teste
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
