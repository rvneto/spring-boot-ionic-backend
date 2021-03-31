package br.com.udemy.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

// Representa a entidade (tabela de um banco de dados por exemplo)
public class Categoria implements Serializable { // Indica que os objetos dela podem ser convertidos em uma sequencia de bytes

    private static final long serialVersionUID = 1;

    private Integer id;
    private String nome;

    public Categoria() {
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
