/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package rbt.emsi.intissar.tpb.tpbanquebenaziz.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.entity.CompteBancaire;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.service.GestionnaireCompte;

/**
 *
 * @author Intissar BENAZIZ
 */
@Named(value = "modifier")
@ViewScoped
public class Modifier implements Serializable {

    private Long id;
    private String nom;
    private int solde;
    private CompteBancaire compteBancaire;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of Modifier
     */
    public Modifier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CompteBancaire getCompte() {
        return compteBancaire;
    }

    public void loadCompte() {
        compteBancaire = gestionnaireCompte.getCompte(id);
    }

    public String modif() {
        compteBancaire.setNom(nom);
        compteBancaire.setSolde(solde);
        gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Nom modifié avec succès : " + nom);
        return "listeComptes?faces-redirect=true";
    }
    
    public String modiff(){
        gestionnaireCompte.modif(compteBancaire, nom, solde);
        Util.addFlashInfoMessage("Nom modifié avec succès : " + nom);
        return "listeComptes?faces-redirect=true";
    }
}
