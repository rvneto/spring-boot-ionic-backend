package br.com.udemy.ionicbackend.services;

import br.com.udemy.ionicbackend.domain.Pedido;
import br.com.udemy.ionicbackend.repositories.PedidoRepository;
import br.com.udemy.ionicbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id); // busca a cliente pelo id
        return pedido.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
