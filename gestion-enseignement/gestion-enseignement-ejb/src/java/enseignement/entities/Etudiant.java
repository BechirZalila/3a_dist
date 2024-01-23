/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zalila
 */
@Entity
@Table (name = "EJB_ENSEIGNEMENT_ETUDIANT")
@NamedQueries({
    @NamedQuery(name = "enseignement.entities.Etudiant.trouverAvecCours",
                query = "SELECT DISTINCT e FROM Etudiant e, IN (e.cours) c"
                      + " WHERE c.titre = :titre")
   ,@NamedQuery(name = "enseignement.entities.Etudiant.trouverAvecEnseignant",
                query = "SELECT DISTINCT e FROM Etudiant e, IN (e.cours) c"
                      + " WHERE c.enseignant.nom = :nom"
                      + " AND c.enseignant.prenom = :prenom")
})
public class Etudiant implements Serializable {

    protected String cin;
    protected String nom;
    protected String prenom;
    protected Date naissance;
    protected Integer niveau;
    protected Collection<Cours> cours;

    public Etudiant() {
    }

    public Etudiant(String cin, String nom, String prenom, Date naissance, Integer niveau) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.niveau = niveau;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    @ManyToMany(mappedBy = "etudiants")
    public Collection<Cours> getCours() {
        return cours;
    }

    public void setCours(Collection<Cours> cours) {
        this.cours = cours;
    }


    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
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

    @Id
    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void ajouterCours (Cours c) {
        this.getCours().add(c);
    }

    public void supprimerCours (Cours c) {
        this.getCours().remove(c);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cin != null ? cin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.cin == null && other.cin != null) || (this.cin != null && !this.cin.equals(other.cin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enseignement.entities.Etudiant[id=" + cin + "]";
    }

}
