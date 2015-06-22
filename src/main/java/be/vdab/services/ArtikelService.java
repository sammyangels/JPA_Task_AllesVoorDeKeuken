package be.vdab.services;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ArtikelService {
    private final ArtikelDAO artikelDAO = new ArtikelDAO();

    public Artikel read(long id) {
        return artikelDAO.read(id);
    }

    public void create(Artikel artikel) {
        artikelDAO.beginTransaction();
        artikelDAO.create(artikel);
        artikelDAO.commit();
    }

    public List<Artikel> findByNaamContains(String woord) {
        return artikelDAO.findByNaamContains(woord);
    }

    public void prijsverhoging(BigDecimal percentage) {
        BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
        artikelDAO.beginTransaction();
        artikelDAO.prijsverhoging(factor);
        artikelDAO.commit();
    }
}
