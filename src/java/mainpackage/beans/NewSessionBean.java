/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import mainpackage.DeviceDto;

/**
 *
 * @author dkory
 */
@Stateless
public class NewSessionBean implements NewSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<DeviceDto> filter(HttpServletRequest request){
        List<DeviceDto> deviceList = deviceList = DeviceDto.deviceList;
        String vendor = Objects.toString(request.getParameter("vendor"), "").trim();
        String model = Objects.toString(request.getParameter("model"), "").trim();
        String dateStr1 = Objects.toString(request.getParameter("date1"), "").trim();
        String dateStr2 = Objects.toString(request.getParameter("date2"), "").trim();
        
        List<DeviceDto> tmpList = new LinkedList<>();
        for(DeviceDto d : deviceList) {
            if(!d.getVendor().contains(vendor)) continue;
            if(!d.getModel().contains(model)) continue;
            if(dateStr1.length()>4) {
                Date date1 = Date.valueOf(dateStr1);
                if(!d.getDate().after(date1)) continue;
            }
            if(dateStr2.length()>4) {
                Date date2 = Date.valueOf(dateStr2);
                if(!d.getDate().before(date2)) continue;
            }
            tmpList.add(d);
        }
        if(!vendor.isEmpty() || !model.isEmpty() || !dateStr1.isEmpty() || !dateStr2.isEmpty()) deviceList = new ArrayList<>(tmpList);
        return deviceList;
    }
}
