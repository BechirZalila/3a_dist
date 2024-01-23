/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncompte.bean;

import javax.ejb.Remote;
import javax.ejb.Stateful;

/**
 *
 * @author zalila
 */
@Remote (GestionCompteRemote.class)
@Stateful (mappedName="ejb/GestionCompte")
public class GestionCompteBean implements GestionCompteRemote {

    protected double solde = 1;

    public double solde() {
        System.out.println("Demande de solde: " + solde);
        return solde;
    }

    ;

    public void crediter(double montant) {
        solde += montant;
        System.out.println("Demande de crédit: " + montant + ". Nouveau solde : " + solde);
    }

    public boolean debiter(double montant) {
        if (solde < montant) {
            System.out.println("Solde insuffisant : " + solde);
            return false;
        }
        solde -= montant;
        System.out.println("Demande de débit: " + montant + ". Nouveau solde : " + solde);
        return true;
    }
}
