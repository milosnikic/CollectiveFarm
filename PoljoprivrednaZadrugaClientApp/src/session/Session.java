/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.User;

/**
 *
 * @author kicnilec
 */
public class Session {

    private User user;
    private static Session instance;

    private Session() {

    }

    public User getUser() {
        return user;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
