/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.requete;

import enseignement.utils.EnseignantInfo;
import enseignement.utils.EtudiantInfo;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author zalila
 */
@Remote
public interface EnsRequeteRemote {

    void creerEtudiant(String cinEtu, String nomEtu, String prenomEtu, Date naissanceEtu, int niveauEtu);

    void creerEnseignant(EnseignantInfo infoEns);

    void creerCours(String nomCours);

    void creerDepartement(String nomDep);

    void ajouterEtudiant(String etudiantCIN, String coursTitre);

    void ajouterEnseignant (String nom, String prenom, String deptTitre);

    void ajouterCours(String coursTitre, String nom, String prenom);
    
    List<EtudiantInfo> trouverEtudiantsSuivantCours(String coursTitre);

    List<EtudiantInfo> trouverEtudiantsSuivantEnseignant(String nom, String prenom);

    List<EnseignantInfo> trouverEnseignantsSuivantEtudiant(String cinEtu);

}
