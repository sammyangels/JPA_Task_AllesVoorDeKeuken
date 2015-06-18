package be.vdab.services;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

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
}
