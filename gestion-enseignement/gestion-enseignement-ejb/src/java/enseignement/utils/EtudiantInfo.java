/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.utils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zalila
 */
public class EtudiantInfo implements Serializable {

    protected String cin;
    protected String nom;
    protected String prenom;
    protected Date naissance;
    protected Integer niveau;

    public EtudiantInfo(String cin, String nom, String prenom, Date naissance, Integer niveau) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.niveau = niveau;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

}
