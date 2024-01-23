/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author zalila
 */
@Entity
@Table (name = "EJB_ENSEIGNEMENT_COURS")
public class Cours implements Serializable {
    protected List<Etudiant> etudiants;
    protected String titre;
    protected Enseignant enseignant;

    public Cours() {
    }

    public Cours(String titre) {
        this.titre = titre;
    }

    @ManyToOne
    public Enseignant getEnseignant() {
        return enseignant;
    }



    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }


    @Id
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titre != null ? titre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cours)) {
            return false;
        }
        Cours other = (Cours) object;
        if ((this.titre == null && other.titre != null) || (this.titre != null && !this.titre.equals(other.titre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enseignement.entities.Cours[id=" + titre + "]";
    }

    @ManyToMany
    @JoinTable(
        name = "EJB_ENSEIGNEMENT_COURS_ETUDIANT",
        joinColumns = @JoinColumn(name = "NOM_COURS",
                                  referencedColumnName = "TITRE"),
        inverseJoinColumns = @JoinColumn(name = "CIN_ETUDIANT",
                                         referencedColumnName = "CIN")
    )
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void ajouterEtudiant (Etudiant e) {
        this.getEtudiants().add(e);
    }

    public void supprimerEtudiant (Etudiant e) {
        this.getEtudiants().remove(e);
    }

}
