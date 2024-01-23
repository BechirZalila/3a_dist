/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coursdevises.tnd;

import javax.ejb.Remote;

/**
 *
 * @author zalila
 */
@Remote
public interface CoursDTNRemote {
    public double toEuro (double d);
    public double fromEuro (double e);
    public double toDollar (double d);
    public double fromDollar (double s);
}
