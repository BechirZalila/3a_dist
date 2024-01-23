/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coursdevises;

import coursdevises.tnd.CoursDTNRemote;
import javax.ejb.EJB;

/**
 *
 * @author zalila
 */
public class Main {

    @EJB (mappedName="ejb/CoursDTNBean")
    private static CoursDTNRemote cours;
    static double m = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            double euro = cours.toEuro(m);
            System.out.println("DTN" + m + " = " + euro + " €.");

            double dollar = cours.toDollar(m);
            System.out.println("DTN" + m + " = " + dollar + " $.");

            double dinar1 = cours.fromEuro(m);
            System.out.println("€" + m + " = " + dinar1 + " DTN.");

            double dinar2 = cours.fromDollar(m);
            System.out.println("$" + m + " = " + dinar2 + " DTN.");

            System.exit(0);
        } catch (Exception ex) {
            System.err.println("Exception non prévue!");
            ex.printStackTrace();
        }
    }
}
