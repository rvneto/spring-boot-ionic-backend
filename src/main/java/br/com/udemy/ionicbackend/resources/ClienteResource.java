package br.com.udemy.ionicbackend.resources;

import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.dto.ClienteDTO;
import br.com.udemy.ionicbackend.dto.ClienteDTO;
import br.com.udemy.ionicbackend.dto.ClienteNewDTO;
import br.com.udemy.ionicbackend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
        Cliente cliente = clienteService.fromDTO(clienteNewDTO);
        cliente = clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        clienteDTO.setId(id);
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente = clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clientesDTO = clientes.stream().map(
                ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientesDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> clientes = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> clientesDTO = clientes.map(ClienteDTO::new);
        return ResponseEntity.ok().body(clientesDTO);
    }

}
