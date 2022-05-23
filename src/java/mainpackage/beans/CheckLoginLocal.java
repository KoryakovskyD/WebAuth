/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage.beans;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dkory
 */
@Local
public interface CheckLoginLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean checkParameters(HttpServletRequest request);
}
