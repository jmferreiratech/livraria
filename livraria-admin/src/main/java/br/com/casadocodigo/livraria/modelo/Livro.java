package br.com.casadocodigo.livraria.modelo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import java.net.URI;
import java.util.Calendar;

@Entity
public class Livro {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String isbn;
    @NotEmpty(message = "{campo.obrigatorio}")
    private String titulo;
    @Embedded
    private Dinheiro preco;
    @Past
    private Calendar dataPublicacao;

    private String descricao;
    private String capa;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Dinheiro getPreco() {
        return preco;
    }

    public void setPreco(Dinheiro preco) {
        this.preco = preco;
    }

    public Calendar getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Calendar dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URI getCapa() {
        if (capa == null) return null;
        return URI.create(capa);
    }

    public void setCapa(URI capa) {
        this.capa = capa == null ? null : capa.toString();
    }
}
