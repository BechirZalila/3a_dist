/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zalila
 */
@IdClass(enseignement.entities.EnseignantPK.class)
@Entity
@Table (name = "EJB_ENSEIGNEMENT_ENSEIGNANT")
@NamedQueries({
    @NamedQuery(name = "enseignement.entities.Enseignant.trouverAvecEtudiant",
                query = "SELECT DISTINCT ens FROM Enseignant ens, IN (ens.cours) c1"
                      + " WHERE EXISTS"
                      + " (SELECT etu FROM Etudiant etu, IN (etu.cours) c2"
                      + "    WHERE c1.titre = c2.titre AND etu.cin = :cinEtu)")
})
public class Enseignant implements Serializable {
    protected List<Cours> cours;
    protected String nom;
    protected String prenom;
    protected String grade;
    protected Departement dpt;

    public Enseignant() {
    }

    public Enseignant(String nom, String prenom, String grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    

    @ManyToOne
    public Departement getDpt() {
        return dpt;
    }

    public void setDpt(Departement dpt) {
        this.dpt = dpt;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Id
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    @Id
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @OneToMany(mappedBy = "enseignant")
    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public void ajouterCours (Cours c) {
        this.getCours().add(c);
    }

    public void supprimerCours (Cours c) {
        this.getCours().remove(c);
    }




}
