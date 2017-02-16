package br.com.casadocodigo.livraria.controlador;


import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.casadocodigo.livraria.aspecto.Transactional;
import br.com.casadocodigo.livraria.modelo.Arquivo;
import br.com.casadocodigo.livraria.modelo.Diretorio;
import br.com.casadocodigo.livraria.modelo.Estante;
import br.com.casadocodigo.livraria.modelo.Livro;
import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

@Resource
public class LivrosController {

    private Estante estante;
    private Result result;
    private Validator validator;
    private Diretorio imagens;

    public LivrosController(Estante estante, Diretorio imagens, Result result, Validator validator) {
        this.estante = estante;
        this.result = result;
        this.validator = validator;
        this.imagens = imagens;
    }

    @Get("/livros/formulario")
    public void formulario() {
    }

    @Transactional
    @Post("/livros")
    public void salva(Livro livro, UploadedFile capa) throws IOException {
        validator.validate(livro);
        validator.onErrorRedirectTo(this).formulario();

        if (capa != null) {
            URI imagemCapa = imagens.grava(new Arquivo(capa.getFileName(), ByteStreams.toByteArray(capa.getFile()), capa.getContentType(), Calendar.getInstance()));
            livro.setCapa(imagemCapa);
        }
        estante.guarda(livro);
        result.include("mensagem", "Livro salvo com sucesso!");
        result.redirectTo(this).lista();
    }

    @Get("/livros")
    public List<Livro> lista() {
        return estante.todosOsLivros();
    }

    @Get
    @Path(value = "/livros/{isbn}", priority = Path.LOWEST)
    public void edita(String isbn) {
        Livro livroEncontrado = estante.buscaPorIsbn(isbn);
        if (null == livroEncontrado) {
            result.notFound();
        } else {
            result.include(livroEncontrado);
            result.of(this).formulario();
        }
    }

    @Get("/livros/{isbn}/capa")
    public Download capa(String isbn) {
        Livro livro = estante.buscaPorIsbn(isbn);
        Arquivo capa = imagens.recupera(livro.getCapa());
        if (null == capa) {
            result.notFound();
            return null;
        }
        return new ByteArrayDownload(capa.getConteudo(), capa.getContentType(), capa.getNome());
    }
}
