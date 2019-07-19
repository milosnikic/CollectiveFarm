/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.GeneralEntity;
import domain.User;
import java.util.LinkedList;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class GetAllUsersSO extends AbstractGenericOperation {

    private LinkedList<GeneralEntity> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof User)) {
            throw new Exception("Invalid object (User)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        list = (LinkedList<GeneralEntity>) databaseBroker.getAll((User) entity, null);
    }

    public LinkedList<GeneralEntity> getList() {
        return list;
    }

}
