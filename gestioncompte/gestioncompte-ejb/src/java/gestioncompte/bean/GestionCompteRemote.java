/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestioncompte.bean;

import javax.ejb.Remote;

/**
 *
 * @author zalila
 */
@Remote
public interface GestionCompteRemote {
    public double solde ();
    public void crediter (double montant);
    public boolean debiter (double montant);
}
