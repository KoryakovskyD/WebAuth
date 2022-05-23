/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage.beans;

import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import mainpackage.DeviceDto;

/**
 *
 * @author dkory
 */
@Local
public interface NewSessionBeanLocal {
    List<DeviceDto> filter(HttpServletRequest request);
}
