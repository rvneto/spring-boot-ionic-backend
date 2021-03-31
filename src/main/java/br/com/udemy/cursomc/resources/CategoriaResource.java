package br.com.udemy.cursomc.resources;

import br.com.udemy.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // Indica que a classe é um controladore REST
@RequestMapping(value = "/categorias") // Indica que responde por esse endpoint
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET) // Indica que será um método GET
    public List<Categoria> listar() {

        Categoria cat1 = new Categoria(1, "Informática");
        Categoria cat2 = new Categoria(2, "Escritório");

        List<Categoria> categorias = new ArrayList<>();
        categorias.add(cat1);
        categorias.add(cat2);

        return categorias;
    }

}
