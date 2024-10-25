package org.example.ejb.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ejb.entity.CD;
import org.example.ejb.entity.Emprunt;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class EmpruntService {
    @PersistenceContext
    private EntityManager em;

    public void emprunterCD(Long cdId, String user) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            cd.setAvailable(false);
            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setUser(user);
            emprunt.setDateEmprunt(LocalDate.now());
            em.persist(emprunt);
        }
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            CD cd = emprunt.getCd();
            cd.setAvailable(true);
            emprunt.setDateRetour(LocalDate.now());
            em.merge(emprunt);
        }
    }

    public List<Emprunt> listEmpruntsByUser(String user) {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.user = :user", Emprunt.class)
                .setParameter("user", user)
                .getResultList();
    }
}