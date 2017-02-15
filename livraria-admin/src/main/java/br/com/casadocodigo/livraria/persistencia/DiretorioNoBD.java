package br.com.casadocodigo.livraria.persistencia;

import br.com.caelum.vraptor.ioc.Component;
import br.com.casadocodigo.livraria.modelo.Arquivo;
import br.com.casadocodigo.livraria.modelo.Diretorio;

import javax.persistence.EntityManager;
import java.net.URI;

@Component
public class DiretorioNoBD implements Diretorio {

    private EntityManager manager;

    public DiretorioNoBD(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public URI grava(Arquivo arquivo) {
        manager.persist(arquivo);
        return URI.create("bd://" + arquivo.getId());
    }

    @Override
    public Arquivo recupera(URI chave) {
        if (null == chave)
            return null;
        if (!chave.getScheme().equals("bd")) {
            throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
        }
        Long id = Long.valueOf(chave.getAuthority());
        return manager.find(Arquivo.class, id);
    }
}
