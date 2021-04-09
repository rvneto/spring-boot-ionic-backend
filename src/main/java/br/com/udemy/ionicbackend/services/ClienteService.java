package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Cidade;
import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.domain.Endereco;
import br.com.udemy.ionicbackend.domain.enums.TipoCliente;
import br.com.udemy.ionicbackend.dto.ClienteDTO;
import br.com.udemy.ionicbackend.dto.ClienteNewDTO;
import br.com.udemy.ionicbackend.repositories.ClienteRepository;
import br.com.udemy.ionicbackend.repositories.EnderecoRepository;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id); // busca a cliente pelo id
        return cliente.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
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
            throw new DataIntegrityException("Não é possível excluir um cliente por que a pedidos relacionados!");
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

    public Cliente fromDTO(ClienteNewDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(),
                clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipoCliente()));

        Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);

        Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(),
                clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);

        cliente.getEnderecos().add(endereco);

        cliente.getTelefones().add(clienteDTO.getTelefone1());

        if (clienteDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteDTO.getTelefone2());
        }

        if (clienteDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteDTO.getTelefone3());
        }

        return cliente;
    }

    private void updateData(Cliente clienteNovo, Cliente cliente) {
        clienteNovo.setNome(cliente.getNome());
        clienteNovo.setEmail(cliente.getEmail());
    }
}
