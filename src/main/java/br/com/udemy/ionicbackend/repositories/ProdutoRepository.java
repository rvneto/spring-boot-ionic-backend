package br.com.udemy.ionicbackend.repositories;

import br.com.udemy.ionicbackend.domain.Categoria;
import br.com.udemy.ionicbackend.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT prod FROM Produto prod INNER JOIN prod.categorias cat WHERE prod.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> search(String nome, List<Categoria> categorias, Pageable pageRequest);

    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
