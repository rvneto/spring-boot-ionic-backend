package br.com.udemy.cursomc.services;

import br.com.udemy.cursomc.domain.Cliente;
import br.com.udemy.cursomc.repositories.ClienteRepository;
import br.com.udemy.cursomc.repositories.ClienteRepository;
import br.com.udemy.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id); // busca a cliente pelo id
        return cliente.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}
