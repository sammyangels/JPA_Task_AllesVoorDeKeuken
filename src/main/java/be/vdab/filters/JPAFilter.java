package be.vdab.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("*.htm")
public class JPAFilter implements Filter {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("allesvoordekeuken");
    private static final ThreadLocal<EntityManager> entityManagers = new ThreadLocal<>();

    @Override
	public void destroy() {
		ENTITY_MANAGER_FACTORY.close();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        entityManagers.set(ENTITY_MANAGER_FACTORY.createEntityManager());
        try {
            request.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        } finally {
            EntityManager entityManager = entityManagers.get();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
            entityManagers.remove();
        }
		
	}

    public static EntityManager getEntityManager() {
        return entityManagers.get();
    }


}
