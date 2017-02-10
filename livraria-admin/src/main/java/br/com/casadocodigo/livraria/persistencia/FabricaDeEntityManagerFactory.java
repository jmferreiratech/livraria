package br.com.casadocodigo.livraria.persistencia;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
@ApplicationScoped
public class FabricaDeEntityManagerFactory implements ComponentFactory<EntityManagerFactory> {

    private final EntityManagerFactory factory;

    public FabricaDeEntityManagerFactory() {
        this.factory = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public EntityManagerFactory getInstance() {
        return this.factory;
    }
}
