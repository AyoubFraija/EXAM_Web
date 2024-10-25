package org.example.ejb;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.example.ejb.entity.CD;
import org.example.ejb.service.CDService;
import org.example.ejb.service.EmpruntService;

import java.util.List;

@Named
@RequestScoped
public class CDBean {
    @EJB
    private CDService cdService;
    @EJB
    private EmpruntService empruntService;

    public List<CD> getCds() {
        return cdService.listAllCDs();
    }

    public void emprunter(Long cdId) {
        empruntService.emprunterCD(cdId, "user1"); // Remplacez "user1" par l'utilisateur actuel
    }
}