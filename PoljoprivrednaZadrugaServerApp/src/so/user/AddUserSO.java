/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.User;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class AddUserSO extends AbstractGenericOperation {
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof User)) {
            throw new Exception("Invalid object (User)");
        }
    }
    
    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        databaseBroker.insert((User) entity);
    }
    
}
