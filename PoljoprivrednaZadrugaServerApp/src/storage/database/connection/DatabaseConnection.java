/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database.connection;

import config.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kicnilec
 */
public class DatabaseConnection {

    private final Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost/poljoprivredna_zadruga";
//        String username = "root";
//        String password = "";
        String url = Configuration.getInstance().getProperty("url");
        String username = Configuration.getInstance().getProperty("username");
        String password = Configuration.getInstance().getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

}
