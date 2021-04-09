package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Categoria;
import br.com.udemy.ionicbackend.dto.CategoriaDTO;
import br.com.udemy.ionicbackend.repositories.CategoriaRepository;
import br.com.udemy.ionicbackend.services.exceptions.DataIntegrityException;
import br.com.udemy.ionicbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Categoria categoriaNova = find(categoria.getId());
        updateData(categoriaNova, categoria);
        return categoriaRepository.save(categoriaNova);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possua produtos!");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoria) {
        return new Categoria(categoria.getId(), categoria.getNome());
    }

    private void updateData(Categoria categoriaNova, Categoria categoria) {
        categoriaNova.setNome(categoria.getNome());
    }
}
