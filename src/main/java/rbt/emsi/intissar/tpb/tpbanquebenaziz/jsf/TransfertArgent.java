/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package rbt.emsi.intissar.tpb.tpbanquebenaziz.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.entity.CompteBancaire;
import rbt.emsi.intissar.tpb.tpbanquebenaziz.service.GestionnaireCompte;

/**
 *
 * @author Intissar BENAZIZ backing bean de la page jsf du transfere d'argent
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private int source;
    private int destination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of TransfereArgent
     */
    public TransfertArgent() {
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Transactional
    public String transferer() {
        CompteBancaire csource = gestionnaireCompte.getCompte(source);
        CompteBancaire cdestination = gestionnaireCompte.getCompte(destination);
        int erreur = 0;
        if (csource == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur++;
        } else {
            if (csource.getSolde() < montant) {
                Util.messageErreur("Solde insuffisant !", "Solde insuffisant sur le compte source pour effectuer le transfert !", "form:montant");
                erreur++;
            }
        }
        if (cdestination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur++;
        }
        if (erreur > 0) {
            return null;
        }
        gestionnaireCompte.transferer(csource, cdestination, montant);

        Util.addFlashInfoMessage("Transfert correctement effectué de " + csource.getNom() + " à " + cdestination.getNom());
        return "listeComptes?faces-redirect=true";
    }

}
