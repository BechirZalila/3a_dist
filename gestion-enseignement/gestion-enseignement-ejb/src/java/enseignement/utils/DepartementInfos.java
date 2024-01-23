/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enseignement.utils;

import java.io.Serializable;

/**
 *
 * @author zalila
 */
public class DepartementInfos implements Serializable {

    protected String nom;

    public DepartementInfos(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
