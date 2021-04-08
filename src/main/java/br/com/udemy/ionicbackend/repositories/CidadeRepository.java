package br.com.udemy.ionicbackend.repositories;

import br.com.udemy.ionicbackend.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Objeto de acesso a dados
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
