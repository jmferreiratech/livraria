package br.com.casadocodigo.livraria.controlador;


import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;

import java.math.BigDecimal;
import java.util.List;

@Resource
public class LivrosController {

    private Estante estante;
    private Result result;
    private Validator validator;

    public LivrosController(Estante estante, Result result, Validator validator) {
        this.estante = estante;
        this.result = result;
        this.validator = validator;
    }

    public void formulario() {
    }

    public void salva(Livro livro) {
        validator.validate(livro);
        validator.onErrorRedirectTo(this).formulario();

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
