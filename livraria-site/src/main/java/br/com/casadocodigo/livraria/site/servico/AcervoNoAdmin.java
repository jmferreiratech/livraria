package br.com.casadocodigo.livraria.site.servico;

import br.com.caelum.vraptor.ioc.Component;
import br.com.casadocodigo.livraria.site.modelo.Acervo;
import br.com.casadocodigo.livraria.site.modelo.Livro;
import com.thoughtworks.xstream.XStream;

import java.util.List;

@Component
public class AcervoNoAdmin implements Acervo {

    private ClienteHTTP http;

    public AcervoNoAdmin(ClienteHTTP http) {
        this.http = http;
    }

    @Override
    public List<Livro> todosOsLivros() {
        String url = "http://localhost:8080/livraria-admin" + "/integracao/listaLivros";
        String resposta = http.get(url);
        XStream xstream = new XStream();
        xstream.alias("livros", List.class);
        xstream.alias("livro", Livro.class);
        return (List<Livro>) xstream.fromXML(resposta);
    }
}
