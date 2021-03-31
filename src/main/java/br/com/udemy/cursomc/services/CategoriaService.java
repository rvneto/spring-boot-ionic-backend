package br.com.udemy.cursomc.services;

import br.com.udemy.cursomc.domain.Categoria;
import br.com.udemy.cursomc.repositories.CategoriaRepository;
import br.com.udemy.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired // Instancia o objeto (Spring)
    private CategoriaRepository categoriaRepository; // Dependencia (injeção do spring)

    public Categoria buscar(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id); // busca a categoria pelo id
        return categoria.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

}
