/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursdevises.tnd;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author zalila
 */
@Stateless (mappedName="ejb/CoursDTNBean")
@Remote(CoursDTNRemote.class)
public class CoursDTNBean implements CoursDTNRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    protected double dinarEuro = 0.51881282;
    protected double dinarDollar = 0.77863428;

    private int nbr = 0;

    public double toEuro(double d) {
        System.out.println(++nbr + " Invocation de 'toEuro'");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CoursDTNBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d * dinarEuro;
    }

    public double fromEuro(double e) {
        System.out.println(++nbr + " Invocation de 'fromEuro'");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CoursDTNBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e / dinarEuro;
    }

    public double toDollar(double d) {
        System.out.println(++nbr + " Invocation de 'toDollar'");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CoursDTNBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d * dinarDollar;
    }

    public double fromDollar(double s) {
        System.out.println (++nbr + " Invocation de 'fromDollar'");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CoursDTNBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s / dinarDollar;
    }
}
