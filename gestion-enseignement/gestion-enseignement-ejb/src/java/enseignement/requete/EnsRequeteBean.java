/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.requete;

import enseignement.entities.Cours;
import enseignement.entities.Departement;
import enseignement.entities.Enseignant;
import enseignement.entities.EnseignantPK;
import enseignement.entities.Etudiant;
import enseignement.utils.EnseignantInfo;
import enseignement.utils.EtudiantInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zalila
 */
@Stateful
public class EnsRequeteBean implements EnsRequeteRemote {

    private static final Logger logger = Logger.getLogger(
                "enseignement.requete.EnsRequeteBean");

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")

    public void creerEtudiant(String cinEtu, String nomEtu, String prenomEtu, Date naissanceEtu, int niveauEtu) {
        logger.info("creerEtudiant: entrée");
        try {
            Etudiant e = new Etudiant(cinEtu, nomEtu, prenomEtu, naissanceEtu, niveauEtu);
            em.persist(e);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        logger.info("creerEtudiant: sortie");
    }

    public void creerEnseignant(EnseignantInfo infoEns) {
        logger.info("creerEnseignant: entrée");
        try {
            Enseignant e = new Enseignant (infoEns.getNom (), infoEns.getPrenom (), infoEns.getGrade ());
            em.persist(e);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        logger.info("creerEnseignant: sortie");
    }

    public void creerCours(String nomCours) {
        logger.info("creerCours: entrée");
        try {
            Cours c = new Cours(nomCours);
            em.persist(c);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        logger.info("creerCours: sortie");
    }

    public void creerDepartement(String nomDep) {
        logger.info("creerDepartement: entrée");
        try {
            Departement d = new Departement(nomDep);
            em.persist(d);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        logger.info("creerDepartement: sortie");
    }

    public void ajouterEtudiant(String etudiantCIN, String coursTitre) {
        logger.info("ajouterEtudiant: entrée");

        try {
            Etudiant e = em.find(Etudiant.class, etudiantCIN);
            Cours c = em.find(Cours.class, coursTitre);

            c.ajouterEtudiant(e);
            e.ajouterCours(c);
        } catch (Exception ex) {
            throw new EJBException
                    ("\nEtudiant : '" +
                      (etudiantCIN == null ? "<null>" : etudiantCIN) + "'" +
                      "\nCours : '" +
                      (coursTitre == null ? "<null>" : coursTitre) + "'\n",
                     ex);
        }
        logger.info("ajouterEtudiant: sortie");
    }

    public void ajouterEnseignant (String nom, String prenom, String deptTitre) {
        logger.info("ajouterEnseignant: entrée");
        try {
            Enseignant e = em.find 
                    (Enseignant.class, new EnseignantPK(nom, prenom));
            Departement d = em.find (Departement.class, deptTitre);

            d.ajouterEnseignant(e);
            e.setDpt(d);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        logger.info("ajouterEnseignant: sortie");
    }

    public void ajouterCours(String coursTitre, String nom, String prenom) {
        logger.info("ajouterCours: entrée");
        try {
            Enseignant e = em.find
                    (Enseignant.class, new EnseignantPK(nom, prenom));
            Cours c = em.find (Cours.class, coursTitre);

            e.ajouterCours(c);
            c.setEnseignant(e);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        logger.info("ajouterCours: sortie");
    }
    
    public List<EtudiantInfo> trouverEtudiantsSuivantCours(String coursTitre) {
        List<Etudiant> etudiants = null;

        try {
            etudiants = (List<Etudiant>) em.createNamedQuery(
                        "enseignement.entities.Etudiant.trouverAvecCours")
                        .setParameter("titre", coursTitre)
                        .getResultList();

            List<EtudiantInfo> einfs = new ArrayList<EtudiantInfo>();
            Iterator<Etudiant> i = etudiants.iterator();

            while (i.hasNext()) {
                Etudiant e = (Etudiant) i.next();
                EtudiantInfo einf = new EtudiantInfo
                        (e.getCin(), e.getNom(), e.getPrenom(), e.getNaissance(), e.getNiveau());

                einfs.add (einf);
            }

            return einfs;
            
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<EtudiantInfo> trouverEtudiantsSuivantEnseignant(String nom, String prenom) {
        List<Etudiant> etudiants = null;

        try {
            etudiants = (List<Etudiant>) em.createNamedQuery(
                        "enseignement.entities.Etudiant.trouverAvecEnseignant")
                        .setParameter("nom", nom)
                        .setParameter("prenom", prenom)
                        .getResultList();

            List<EtudiantInfo> einfs = new ArrayList<EtudiantInfo>();
            Iterator<Etudiant> i = etudiants.iterator();

            while (i.hasNext()) {
                Etudiant e = (Etudiant) i.next();
                EtudiantInfo einf = new EtudiantInfo
                        (e.getCin(), e.getNom(), e.getPrenom(), e.getNaissance(), e.getNiveau());

                einfs.add (einf);
            }

            return einfs;

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<EnseignantInfo> trouverEnseignantsSuivantEtudiant(String cinEtu) {
        List<Enseignant> enseignants = null;

        try {
            enseignants = (List<Enseignant>) em.createNamedQuery(
                        "enseignement.entities.Enseignant.trouverAvecEtudiant")
                        .setParameter("cinEtu", cinEtu)
                        .getResultList();

            List<EnseignantInfo> einfs = new ArrayList<EnseignantInfo>();
            Iterator<Enseignant> i = enseignants.iterator();

            while (i.hasNext()) {
                Enseignant e = (Enseignant) i.next();
                EnseignantInfo einf = new EnseignantInfo
                        (e.getNom(), e.getPrenom(), e.getGrade());

                einfs.add (einf);
            }

            return einfs;

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

}
