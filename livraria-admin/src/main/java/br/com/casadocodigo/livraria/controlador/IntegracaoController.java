package br.com.casadocodigo.livraria.controlador;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;

import java.util.List;

@Resource
public class IntegracaoController {

    private Estante estante;
    private Result result;

    public IntegracaoController(Estante estante, Result result) {
        this.estante = estante;
        this.result = result;
    }

    public void listaLivros() {
        List<Livro> livros = estante.todosOsLivros();
        result.use(Results.xml()).from(livros, "livros").serialize();
    }
}
