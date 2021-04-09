package br.com.udemy.ionicbackend.repositories;

import br.com.udemy.ionicbackend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // não necessita ser envolvida como uma transação de BD / performance, não locka
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);

}
