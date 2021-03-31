package br.com.udemy.cursomc.resources;

import br.com.udemy.cursomc.domain.Categoria;
import br.com.udemy.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que a classe é um controladore REST
@RequestMapping(value = "/categorias") // Indica que responde por esse endpoint
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Indica que será um método GET
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscar(id);
        return ResponseEntity.ok().body(categoria);
    }

}
