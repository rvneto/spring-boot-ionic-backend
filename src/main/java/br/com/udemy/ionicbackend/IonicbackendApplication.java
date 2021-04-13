package br.com.udemy.ionicbackend;

import br.com.udemy.ionicbackend.domain.*;
import br.com.udemy.ionicbackend.domain.enums.EstadoPagamento;
import br.com.udemy.ionicbackend.domain.enums.TipoCliente;
import br.com.udemy.ionicbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class IonicbackendApplication implements CommandLineRunner { // Permite implementar um metodo auxiliar para executar alguma ação

    public static void main(String[] args) {
        SpringApplication.run(IonicbackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
