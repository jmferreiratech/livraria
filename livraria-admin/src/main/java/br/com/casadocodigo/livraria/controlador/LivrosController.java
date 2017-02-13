package br.com.casadocodigo.livraria.controlador;


import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;

import java.util.List;

@Resource
public class LivrosController {

    private Estante estante;
    private Result result;

    public LivrosController(Estante estante, Result result) {
        this.estante = estante;
        this.result = result;
    }

    public void formulario() {
    }

    public void salva(Livro livro) {
        estante.guarda(livro);
        result.include("mensagem", "Livro salvo com sucesso!");
        result.redirectTo(this).lista();
    }

    public List<Livro> lista() {
        return estante.todosOsLivros();
    }

    public void edita(String isbn) {
        Livro livroEncontrado = estante.buscaPorIsbn(isbn);
        if (null == livroEncontrado) {
            result.notFound();
        } else {
            result.include(livroEncontrado);
            result.of(this).formulario();
        }
    }
}
