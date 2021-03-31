package br.com.udemy.cursomc;

import br.com.udemy.cursomc.domain.Categoria;
import br.com.udemy.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // Permite implementar um metodo auxiliar para executar alguma ação

    @Autowired
    CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(1, "Informática");
        Categoria cat2 = new Categoria(2, "Escritório");

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

    }
}
