package br.com.udemy.ionicbackend.resources;

import br.com.udemy.ionicbackend.domain.Categoria;
import br.com.udemy.ionicbackend.domain.Produto;
import br.com.udemy.ionicbackend.dto.CategoriaDTO;
import br.com.udemy.ionicbackend.dto.ProdutoDTO;
import br.com.udemy.ionicbackend.resources.utils.URL;
import br.com.udemy.ionicbackend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(name = "nome", defaultValue = "") String nome,
            @RequestParam(name = "categorias", defaultValue = "") String categorias,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Produto> produtos = produtoService.search(URL.decodeParam(nome), URL.decodeIntList(categorias), page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> produtosDTO = produtos.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(produtosDTO);
    }

}
