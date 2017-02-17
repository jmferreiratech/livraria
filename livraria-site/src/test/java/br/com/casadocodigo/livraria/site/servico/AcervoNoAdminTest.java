package br.com.casadocodigo.livraria.site.servico;

import br.com.casadocodigo.livraria.site.modelo.Livro;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AcervoNoAdminTest {

    @Test
    public void converteUmaListaComApenasUmLivro() throws Exception {
        ClienteHTTP http = new ClienteHTTP() {
            @Override
            public String get(String url) {
                return
                        "<livros>" +
                            "<livro>" +
                                "<titulo>VRaptor 3</titulo>" +
                                "<isbn>12345</isbn>" +
                            "</livro>" +
                        "</livros>";
            }
        };
        AcervoNoAdmin acervo = new AcervoNoAdmin(http);
        List<Livro> livros = acervo.todosOsLivros();
        assertThat(livros.size(), is(1));
        Livro livro = livros.get(0);
        assertThat(livro.getTitulo(), is("VRaptor 3"));
        assertThat(livro.getIsbn(), is("12345"));
    }
}
