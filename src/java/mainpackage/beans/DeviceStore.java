/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage.beans;

import java.sql.Date;
import java.util.List;
import javax.ejb.Singleton;
import mainpackage.DeviceDto;
import static mainpackage.DeviceDto.deviceList;

/**
 *
 * @author dkory
 */
@Singleton
public class DeviceStore implements DeviceStoreLocal {

    
    @Override
    public void createDevice(String vendor, String model, Date date) {
       int id=0;
        for(DeviceDto d : deviceList) {
            id = d.getId() > id ? d.getId() : id;
        }
       DeviceDto.deviceList.add(new DeviceDto(id, vendor, model, date));
    }

    @Override
    public boolean updateDevice(int id, String vendor, String model, Date date) {
        DeviceDto device = getById(id);
        if (device==null) return false;
        device.setVendor(vendor);
        device.setModel(model);
        device.setDate(date);
        return true;
    }

    @Override
    public boolean updateDevice(DeviceDto device) {
  
        if (device==null) return false;
        
        DeviceDto dev = getById(device.getId());
        dev.setVendor(device.getVendor());
        dev.setModel(device.getModel());
        dev.setDate(device.getDate());
        return true;
    }

    @Override
    public void deleteDevice(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDevice(DeviceDto device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeviceDto getById(int id) {
        DeviceDto device = null;
        for(DeviceDto d : deviceList) {
            if (d.getId()==id) device = d;
        }
        return device;
    }

    @Override
    public List<DeviceDto> getAllDevice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
