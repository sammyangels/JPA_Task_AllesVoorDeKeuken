package be.vdab.dao;

import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

abstract class AbstractDAO {
    protected EntityManager getEntityManager() {
        return JPAFilter.getEntityManager();
    }
    public void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }
    public void commit() {
        getEntityManager().getTransaction().commit();
    }
    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }
}
