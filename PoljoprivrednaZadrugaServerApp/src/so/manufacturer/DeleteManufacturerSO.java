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
public class DeleteManufacturerSO extends AbstractGenericOperation {

    private boolean success;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Long)) {
            throw new Exception("Invalid object (Long)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Manufacturer m = new Manufacturer();
        m.setId((Long) entity);
        Manufacturer manufacturer = (Manufacturer) databaseBroker.getById(m, null);
        if (manufacturer != null) {
            databaseBroker.delete((Manufacturer) manufacturer);
            success = true;
        } else {
            success = false;
        }

    }

    public boolean isSuccess() {
        return success;
    }

}
