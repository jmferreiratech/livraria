package br.com.casadocodigo.livraria.aspecto;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import javax.persistence.EntityManager;

@Intercepts
@Lazy
public class TransacoesInterceptor implements Interceptor {

    private EntityManager manager;

    public TransacoesInterceptor(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object controller) throws InterceptionException {
        try {
            manager.getTransaction().begin();
            stack.next(method, controller);
            manager.getTransaction().commit();
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }
    }

    @Override
    public boolean accepts(ResourceMethod resourceMethod) {
        return resourceMethod.containsAnnotation(Transactional.class);
    }
}
