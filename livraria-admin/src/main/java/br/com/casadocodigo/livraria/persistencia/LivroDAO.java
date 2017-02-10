package br.com.casadocodigo.livraria.persistencia;

import br.com.casadocodigo.livraria.modelo.Livro;

import java.util.List;

public interface LivroDAO {
    void adiciona(Livro livro);

    List<Livro> todos();

    Livro buscaPorIsbn(String isbn);
}
