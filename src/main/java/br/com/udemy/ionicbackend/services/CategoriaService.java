package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Categoria;
import br.com.udemy.ionicbackend.repositories.CategoriaRepository;
import br.com.udemy.ionicbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired // Instancia o objeto (Spring)
    private CategoriaRepository categoriaRepository; // Dependencia (injeção do spring)

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id); // busca a categoria pelo id
        return categoria.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        find(categoria.getId());
        return categoriaRepository.save(categoria);
    }

}
