/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package rbt.emsi.intissar.tpb.tpbanquebenaziz.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.entity.CompteBancaire;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.service.GestionnaireCompte;

/**
 *
 * @author Intissar BENAZIZ
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte implements Serializable {

    String nom;
    @PositiveOrZero
    int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String ajouter() {
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setNom(nom);
        compteBancaire.setSolde(solde);
        gestionnaireCompte.creerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte créé avec succès : " + nom);
        return "listeComptes?faces-redirect=true";
    }
}
