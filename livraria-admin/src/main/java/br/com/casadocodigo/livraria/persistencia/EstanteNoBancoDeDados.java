package br.com.casadocodigo.livraria.persistencia;

import br.com.caelum.vraptor.ioc.Component;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;

import java.util.List;

@Component
public class EstanteNoBancoDeDados implements Estante {

    private final LivroDAO dao;

    public EstanteNoBancoDeDados(LivroDAO dao) {
        this.dao = dao;
    }

    @Override
    public void guarda(Livro livro) {
        dao.adiciona(livro);
    }

    @Override
    public List<Livro> todosOsLivros() {
        return dao.todos();
    }

    @Override
    public Livro buscaPorIsbn(String isbn) {
        return dao.buscaPorIsbn(isbn);
    }
}
