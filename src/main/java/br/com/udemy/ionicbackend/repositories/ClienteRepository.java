package br.com.udemy.ionicbackend.repositories;

import br.com.udemy.ionicbackend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Objeto de acesso a dados
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
