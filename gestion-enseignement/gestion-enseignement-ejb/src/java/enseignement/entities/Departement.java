/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enseignement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zalila
 */
@Entity
@Table(name = "EJB_ENSEIGNEMENT_DEPARTEMENT")
public class Departement implements Serializable {

    protected List<Enseignant> enseignants;
    protected List<Cours> cours;
    protected String nom;

    public Departement() {
    }

    public Departement(String nom) {
        this.nom = nom;
    }

    @Id
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nom != null ? nom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.nom == null && other.nom != null) || (this.nom != null && !this.nom.equals(other.nom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enseignement.entities.Departement[id=" + nom + "]";
    }

    @OneToMany
    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    @OneToMany(mappedBy = "dpt")
    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public void ajouterCours(Cours c) {
        this.getCours().add(c);
    }

    public void supprimerCours(Cours c) {
        this.getCours().remove(c);
    }

    public void ajouterEnseignant (Enseignant e) {
        this.getEnseignants().add(e);
    }

    public void supprimerEnseignant(Enseignant e) {
        this.getEnseignants().remove(e);
    }
}
