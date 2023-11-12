/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rbt.emsi.intissar.tpb.tpbanquebenaziz.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.entity.CompteBancaire;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.service.GestionnaireCompte;

/**
 *
 * @author Intissar BENAZIZ bean cdi pour créer 4 comptes au démarrage de
 * l'application si la table des comptes est vide.
 */
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @Transactional
    public void init(@Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        if (gestionnaireCompte.nbComptes() == 0) {
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaireCompte.creerCompte(new CompteBancaire("George Harrison", 100000));
        }
    }
}
