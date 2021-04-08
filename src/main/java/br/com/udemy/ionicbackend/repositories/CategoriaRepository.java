package br.com.udemy.ionicbackend.repositories;

import br.com.udemy.ionicbackend.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Objeto de acesso a dados
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
