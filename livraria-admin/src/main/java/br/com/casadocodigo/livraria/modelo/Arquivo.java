package br.com.casadocodigo.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Calendar;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private byte[] conteudo;

    private String nome;
    private String contentType;
    private Calendar dataModificacao;

    public Arquivo() {
    }

    public Arquivo(String nome, byte[] conteudo, String contentType, Calendar dataModificacao) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.contentType = contentType;
        this.dataModificacao = dataModificacao;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Calendar getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Calendar dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
