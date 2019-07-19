/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.GeneralEntity;
import domain.User;
import so.AbstractGenericOperation;

/**
 *
 * @author kicnilec
 */
public class LoginSO extends AbstractGenericOperation {

    private User user;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof User)) {
            throw new Exception("Invalid object (User)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        String condition = keyword;
        user = (User) databaseBroker.getByCondition((User)entity, null, condition).get(0);
    }

    public User getUser() {
        return user;
    }

}
