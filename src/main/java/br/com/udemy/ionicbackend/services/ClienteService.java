package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.dto.ClienteDTO;
import br.com.udemy.ionicbackend.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id); // busca a cliente pelo id
        return cliente.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteNovo = find(cliente.getId());
        updateData(clienteNovo, cliente);
        return clienteRepository.save(clienteNovo);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente por que a entidades relacionadas!");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO cliente) {
        return new Cliente(cliente.getId(), cliente.getNome(), cliente.getEmail(), null, null);
    }

    private void updateData(Cliente clienteNovo, Cliente cliente) {
        clienteNovo.setNome(cliente.getNome());
        clienteNovo.setEmail(cliente.getEmail());
    }
}
