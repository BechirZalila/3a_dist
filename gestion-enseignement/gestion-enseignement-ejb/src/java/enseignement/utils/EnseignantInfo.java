/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.utils;

import java.io.Serializable;

/**
 *
 * @author zalila
 */
public class EnseignantInfo implements Serializable {
    protected String nom;
    protected String prenom;
    protected String grade;

    public EnseignantInfo() {
    }

    

    public EnseignantInfo(String nom, String prenom, String grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

}
