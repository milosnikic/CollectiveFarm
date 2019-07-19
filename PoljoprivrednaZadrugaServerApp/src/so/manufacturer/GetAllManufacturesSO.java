/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.manufacturer;

import domain.GeneralEntity;
import domain.Manufacturer;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class GetAllManufacturesSO extends AbstractGenericOperation {

    private LinkedList<GeneralEntity> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Manufacturer)) {
            throw new Exception("Invalid object (Manufacturer)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        list = (LinkedList<GeneralEntity>) databaseBroker.getAll((Manufacturer) entity, null);
    }

    public LinkedList<GeneralEntity> getList() {
        return list;
    }

}
