/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.product;

import domain.GeneralEntity;
import domain.Product;
import domain.User;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class GetAllProductsSO extends AbstractGenericOperation {

    private LinkedList<GeneralEntity> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Product)) {
            throw new Exception("Invalid object (Product)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        list = (LinkedList<GeneralEntity>) databaseBroker.getAll((Product) entity, null);
        for (GeneralEntity generalEntity : list) {
            Product p = (Product) generalEntity;
            User user = (User) databaseBroker.getById(p.getSavedUser(), null);
            p.setSavedUser(user);
        }
    }

    public LinkedList<GeneralEntity> getList() {
        return list;
    }
}
