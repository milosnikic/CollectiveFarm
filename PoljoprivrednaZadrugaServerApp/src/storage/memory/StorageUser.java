/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.memory;

import domain.User;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kicnilec
 */
public class StorageUser {

    private List<User> users;

    public StorageUser() {
        users = new LinkedList<>();
        initUsers();
    }

    private void initUsers() {
//        users.add(new User(1L, "Miloš", "Nikić", "milos", "milos", "milos.nikic@gmail.com"));
//        users.add(new User(2L, "Jovan", "Jovanović", "joca", "joca", "joca@gmail.com"));
    }

    public List<User> getUsers() {
        return users;
    }
}
