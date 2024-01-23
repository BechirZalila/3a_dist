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
public class CoursInfo implements Serializable {

    protected String titre;

    public CoursInfo(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
