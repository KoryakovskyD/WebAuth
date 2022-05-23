/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage.beans;

import java.sql.Date;
import java.util.List;
import javax.ejb.Local;
import mainpackage.DeviceDto;

/**
 *
 * @author dkory
 */
@Local
public interface DeviceStoreLocal {
    void createDevice(String vendor, String model, Date date);
    
    boolean updateDevice(int id, String vendor, String model, Date date);
    
    boolean updateDevice(DeviceDto device);
    
    void deleteDevice(int id);
    
    void deleteDevice(DeviceDto device);
    
    DeviceDto getById(int id);
    
    List<DeviceDto> getAllDevice();
}
