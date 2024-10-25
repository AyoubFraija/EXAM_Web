package org.example.ejb.service;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ejb.entity.CD;

import java.util.List;

@Stateful
public class CDService {
    @PersistenceContext
    private EntityManager em;

    public void addCD(CD cd) {
        em.persist(cd);
    }

    public void updateCD(CD cd) {
        em.merge(cd);
    }

    public void deleteCD(Long id) {
        CD cd = em.find(CD.class, id);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<CD> listAllCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}