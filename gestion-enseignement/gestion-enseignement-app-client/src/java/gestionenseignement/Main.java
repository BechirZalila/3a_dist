/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionenseignement;

import enseignement.requete.EnsRequeteRemote;
import enseignement.utils.EnseignantInfo;
import enseignement.utils.EtudiantInfo;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author zalila
 */
public class Main {

    @EJB
    private static EnsRequeteRemote req;

    static String grades [] = {"Assistant",
                               "Maitre-Assistant",
                               "Maitre de conférences",
                               "Professeur"};

    static String depts [] = {"DGIMA",
                              "DGELEC",
                              "DGMAT",
                              "DGMEC",
                              "DGBIO",
                              "DGGEO"};

    static String cours [] = {"DGIMA_Cours_01",
                              "DGIMA_Cours_02",
                              "DGIMA_Cours_03",
                              "DGIMA_Cours_04",
                              "DGIMA_Cours_05",
                              "DGIMA_Cours_06",
                              "DGELEC_Cours_07",
                              "DGELEC_Cours_08",
                              "DGELEC_Cours_09",
                              "DGELEC_Cours_10",
                              "DGELEC_Cours_11",
                              "DGELEC_Cours_12",
                              "DGELEC_Cours_13",
                              "DGMAT_Cours_14",
                              "DGMAT_Cours_15",
                              "DGMAT_Cours_16",
                              "DGMAT_Cours_17",
                              "DGMEC_Cours_18",
                              "DGMEC_Cours_19",
                              "DGMEC_Cours_20",
                              "DGMEC_Cours_21",
                              "DGMEC_Cours_22",
                              "DGBIO_Cours_23",
                              "DGBIO_Cours_24",
                              "DGBIO_Cours_25",
                              "DGGEO_Cours_26",
                              "DGGEO_Cours_27"};

    static final int NB_ENSEIGNANTS = 15;
    static final int NB_ETUDIANTS = 360;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
        DecimalFormat deuxChiffres = new DecimalFormat ("00");
        DecimalFormat troisChiffres = new DecimalFormat ("000");

        try {
            // Ajout d'étudiants
            for (int i = 1; i <= NB_ETUDIANTS; i++) {
                String idx = troisChiffres.format (i);
                String idx2 = deuxChiffres.format((i % 12) + 1);
                String idx3 = deuxChiffres.format((i % 28) + 1);

                req.creerEtudiant("08123" + idx,
                                  "Nom_" + idx,
                                  "Prenom_" + idx,
                                  df.parse ("1986-" + idx2 + "-" + idx),
                                  (i % 3) + 1);
            }

            // Ajout d'enseignants
            for (int i = 1; i <= NB_ENSEIGNANTS; i++) {
                String idx = deuxChiffres.format(i);

                req.creerEnseignant (new EnseignantInfo
                                          ("Nom_" + idx,
                                           "Prenom_" + idx, 
                                           grades [i % 4]));

            }

            // Ajouts de départements
            for (int i = 0; i < depts.length; i ++) {
                req.creerDepartement(depts [i]);
            }

            // Ajout de cours
            for (int i = 0; i < cours.length; i++) {
                req.creerCours (cours [i]);
            }

            // Correspondance etudiant/cours
            for (int i = 1; i <= NB_ETUDIANTS; i++) {
                String idx = troisChiffres.format(i);

                req.ajouterEtudiant("08123" + idx,
                                    cours [i % cours.length]);

            }

            // Correspondance enseignant/departement
            for (int i = 1; i <= NB_ENSEIGNANTS; i++) {
                String idx = deuxChiffres.format(i);

                req.ajouterEnseignant("Nom_" + idx,
                                      "Prenom_" + idx,
                                      depts [i % depts.length]);

            }

            // Correspondance cours/enseignant
            for (int i = 0; i < cours.length; i++) {
                String idx = deuxChiffres.format((i % NB_ENSEIGNANTS) + 1);

                req.ajouterCours(cours [i],
                                 "Nom_" + idx,
                                 "Prenom_" + idx);

            }

            // Chercher quelques etudiants selon leur cours
            String coursCherche = cours [8];
            List<EtudiantInfo> l = req.trouverEtudiantsSuivantCours
                    (coursCherche);

            System.out.println("Etudiants ayant suivi le cours : '"
                             + coursCherche
                             + "'\n");
            Iterator<EtudiantInfo> i = l.iterator();
            while (i.hasNext()) {
                EtudiantInfo einf = (EtudiantInfo) i.next ();
                System.out.println(" Trouvé : \n"
                        + "  CIN       : " + einf.getCin() + "\n"
                        + "  Nom       : " + einf.getNom() + "\n"
                        + "  Prenom    : " + einf.getPrenom() + "\n"
                        + "  Naissance : " + einf.getNaissance() + "\n"
                        + "  Niveau    : " + einf.getNiveau() + "\n");
            }

            // Chercher quelques etudiants selon leur enseignant
            String nomCherche    = "Nom_11";
            String prenomCherche = "Prenom_11";
            List<EtudiantInfo> l2 = req.trouverEtudiantsSuivantEnseignant
                    (nomCherche, prenomCherche);

            System.out.println("Etudiants ayant comme enseignant : '"
                             + nomCherche + "' '" + prenomCherche
                             + "'\n");
            Iterator<EtudiantInfo> j = l2.iterator();
            while (j.hasNext()) {
                EtudiantInfo einf = (EtudiantInfo) j.next ();
                System.out.println(" Trouvé : \n"
                        + "  CIN       : " + einf.getCin() + "\n"
                        + "  Nom       : " + einf.getNom() + "\n"
                        + "  Prenom    : " + einf.getPrenom() + "\n"
                        + "  Naissance : " + einf.getNaissance() + "\n"
                        + "  Niveau    : " + einf.getNiveau() + "\n");
            }

            // Chercher quelques enseignants selon leur etudiant
            String cinCherche    = "08123076";
            List<EnseignantInfo> l3 = req.trouverEnseignantsSuivantEtudiant
                    (cinCherche);

            System.out.println("Enseignants ayant comme étudiant : '"
                             + cinCherche
                             + "'\n");
            Iterator<EnseignantInfo> k = l3.iterator();
            while (k.hasNext()) {
                EnseignantInfo einf = (EnseignantInfo) k.next ();
                System.out.println(" Trouvé : \n"
                        + "  Nom       : " + einf.getNom() + "\n"
                        + "  Prenom    : " + einf.getPrenom() + "\n"
                        + "  Grade     : " + einf.getGrade() + "\n");
            }

            System.exit(0);
        } catch (Exception ex) {
            System.err.println("Exception:");
            ex.printStackTrace();
        }

    }

}
