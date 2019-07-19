/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.AbstractStorageUser;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author kicnilec
 */
public class DatabaseStorageUser extends AbstractStorageUser {


    public User login(String username, String password) throws Exception {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                User user = new User(id, firstname, lastname, username, password, email);
                resultSet.close();
                return user;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        throw new Exception("Invalid username/password");
    }

    public User findUserByUsername(User newUser) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM user WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newUser.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new User(id, firstname, lastname, username, password, email);
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    

    public void addUser(User newUser) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "INSERT INTO user(firstname,lastname,username,password,email) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newUser.getFirstname());
            preparedStatement.setString(2, newUser.getLastname());
            preparedStatement.setString(3, newUser.getUsername());
            preparedStatement.setString(4, newUser.getPassword());
            preparedStatement.setString(5, newUser.getEmail());
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static User getUserById(Long id) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new User(id, firstname, lastname, username, password, email);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public LinkedList<User> getAllUsers() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM user";
        LinkedList<User> users = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                users.add(new User(id, firstname, lastname, username, password, email));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
