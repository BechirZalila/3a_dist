/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncompte.utils;

import gestioncompte.bean.GestionCompteRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zalila
 */
public class GestionCompteUtils {

    private static GestionCompteRemote compte;
    private static boolean compteConnecte = false;

    private static void connecterCompte() {
        if (!compteConnecte) {
            compteConnecte = true;

            InitialContext ctx;
            try {
                ctx = new InitialContext();
                compte = (GestionCompteRemote) ctx.lookup("ejb/GestionCompte");
            } catch (NamingException ex) {
                Logger.getLogger(GestionCompteUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static double solde() {
        connecterCompte();
        return compte.solde();
    }

    public static void crediter(double montant) {
        connecterCompte();
        compte.crediter(montant);
    }

    public static boolean debiter(double montant) {
        connecterCompte();
        return compte.debiter(montant);
    }
}
