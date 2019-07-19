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
public class FindUserByUsernameSO extends AbstractGenericOperation {

    private User user;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof User)) {
            throw new Exception("Invalid object (User)");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        String condition = "username='" + ((User) entity).getUsername() + "'";
        LinkedList<GeneralEntity> list = (LinkedList<GeneralEntity>) databaseBroker.getByCondition(new User(), null, condition);
        if(list.size() > 0){
            user = (User) list.get(0);
        }else{
            user = null;
        }
    }

    public User getUser() {
        return user;
    }

}
