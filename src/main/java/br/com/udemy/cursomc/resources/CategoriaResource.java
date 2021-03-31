package br.com.udemy.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que a classe é um controladore REST
@RequestMapping(value = "/categorias") // Indica que responde por esse endpoint
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET) // Indica que será um método GET no método
    public String listar() {
        return "REST está funcionando!";
    }

}
