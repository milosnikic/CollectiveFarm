/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.MeasurementUnit;
import domain.Product;
import domain.ProductCategory;
import domain.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.AbstractStorageProduct;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author kicnilec
 */
public class DatabaseStorageProduct extends AbstractStorageProduct{

    public void saveProduct(Product product) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "INSERT INTO product(name,description,price,user_id,category,measurement_unit) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setLong(4, product.getSavedUser().getId());
            preparedStatement.setString(5, String.valueOf(product.getCategory()));
            preparedStatement.setString(6, String.valueOf(product.getMeasurementUnit()));
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw new Exception("Save product exception: " + ex.getMessage());
        }
    }

    public List getAllProducts() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM product JOIN user u ON product.user_id = u.id ORDER BY product.name";
        List<Product> products = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");

                MeasurementUnit measurementUnit = MeasurementUnit.valueOf(resultSet.getString("measurement_unit"));
                ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));

                Long user_id = resultSet.getLong("u.id");
                String username = resultSet.getString("u.username");
                String password = resultSet.getString("u.password");
                String firstname = resultSet.getString("u.firstname");
                String lastname = resultSet.getString("u.lastname");
                String email = resultSet.getString("u.email");
                User savedUser = new User(user_id, firstname, lastname, username, password, email);
                Product product = new Product(id, name, description, price, measurementUnit, savedUser, category);
                products.add(product);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "UPDATE product p SET "
                + "p.name = ?,"
                + "p.description = ?,"
                + "p.price = ?,"
                + "p.user_id = ?,"
                + "p.category = ?,"
                + "p.measurement_unit = ?"
                + "WHERE p.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setLong(4, product.getSavedUser().getId());
            preparedStatement.setString(5, String.valueOf(product.getCategory()));
            preparedStatement.setString(6, String.valueOf(product.getMeasurementUnit()));
            preparedStatement.setLong(7, product.getId());
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean deleteProduct(Long id) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "DELETE FROM product WHERE id = ?";
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
