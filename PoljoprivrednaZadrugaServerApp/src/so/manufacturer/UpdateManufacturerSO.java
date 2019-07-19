/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.manufacturer;

import domain.Manufacturer;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class UpdateManufacturerSO extends AbstractGenericOperation {
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Manufacturer)) {
            throw new Exception("Invalid object (Manufacturer)");
        }
    }
    
    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        databaseBroker.update((Manufacturer) entity);
    }
    
}
