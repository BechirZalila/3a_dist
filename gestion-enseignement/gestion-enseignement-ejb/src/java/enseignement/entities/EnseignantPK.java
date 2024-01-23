/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.entities;

import java.io.Serializable;

/**
 *
 * @author zalila
 */
public class EnseignantPK implements Serializable {

    protected String nom;
    protected String prenom;

    public EnseignantPK() {
    }

    public EnseignantPK(String nom, String prenom) {
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    @Override
    public int hashCode() {
        return (((this.getNom() == null) ? 0 : this.getPrenom()
                                                       .hashCode())
        ^ (this.getNom().hashCode()));
    }

    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {
            return true;
        }

        if (!(otherOb instanceof EnseignantPK)) {
            return false;
        }

        EnseignantPK other = (EnseignantPK) otherOb;

        return (((this.getNom() == null) ?
            (other.getNom() == null) :
            this.getNom().equals(other.getNom())) &&
              (this.getPrenom() == null ?
                  (other.getPrenom() == null) : 
                  this.getPrenom().equals (other.getPrenom())));
    }

    public String toString() {
        return "" + getNom() + "-" + getPrenom();
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
