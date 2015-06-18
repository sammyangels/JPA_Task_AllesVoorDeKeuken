package be.vdab.dao;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

import static be.vdab.filters.JPAFilter.getEntityManager;

public class ArtikelDAO extends AbstractDAO {
    public Artikel read(long id) {
            return getEntityManager().find(Artikel.class, id);
    }

    public void create(Artikel artikel) {
        getEntityManager().persist(artikel);
    }
}
