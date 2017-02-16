package br.com.casadocodigo.livraria.persistencia;

import br.com.caelum.vraptor.ioc.Component;
import br.com.casadocodigo.livraria.modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Component
public class JPALivroDAO implements LivroDAO {
    private final EntityManager manager;

    public JPALivroDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void adiciona(Livro livro) {
        if (null == livro.getId()) {
            manager.persist(livro);
        } else {
            manager.merge(livro);
        }
    }

    @Override
    public List<Livro> todos() {
        return manager.createQuery("select l from Livro l", Livro.class).getResultList();
    }

    @Override
    public Livro buscaPorIsbn(String isbn) {
        try {
            return manager.createQuery("select l from Livro l where l.isbn = :isbn", Livro.class).setParameter("isbn", isbn).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
