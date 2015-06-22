package be.vdab.dao;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

import static be.vdab.filters.JPAFilter.getEntityManager;

public class ArtikelDAO extends AbstractDAO {
    public Artikel read(long id) {
            return getEntityManager().find(Artikel.class, id);
    }

    public void create(Artikel artikel) {
        getEntityManager().persist(artikel);
    }

    public List<Artikel> findByNaamContains(String woord) {
        return getEntityManager()
                .createNamedQuery("Artikel.findByNaamContains", Artikel.class)
                .setParameter("zoals", '%' + woord + '%').getResultList();
    }

    public void prijsverhoging(BigDecimal factor) {
        getEntityManager().createNamedQuery("Artikel.prijsverhoging")
                .setParameter("factor", factor)
                .executeUpdate();
    }
}
