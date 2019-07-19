/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.AbstractStorageManufacturer;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author kicnilec
 */
public class DatabaseStorageManufacturer extends AbstractStorageManufacturer{

    public List<Manufacturer> getAllManufactures() {
        LinkedList<Manufacturer> manufactures = new LinkedList<>();
        Connection connection = null;
        String query = "SELECT * FROM manufacturer";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String place = resultSet.getString("place");
                Manufacturer manufacturer = new Manufacturer(id, name, address, place);
                manufactures.add(manufacturer);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        return manufactures;
    }

    public void saveManufacturer(Manufacturer manufacturer) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "INSERT INTO manufacturer (name,address,place) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getAddress());
            preparedStatement.setString(3, manufacturer.getPlace());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            connection.rollback();
        }
    }

    public void updateManufacturer(Manufacturer manufacturer) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "UPDATE manufacturer m SET "
                    + "m.name = ?,"
                    + "m.address = ?,"
                    + "m.place = ?"
                    + "WHERE m.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getAddress());
            preparedStatement.setString(3, manufacturer.getPlace());
            preparedStatement.setLong(4, manufacturer.getId());
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            connection.rollback();
        }
    }

    public boolean deleteManufacturer(Long id) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "DELETE FROM manufacturer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            connection.rollback();
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
