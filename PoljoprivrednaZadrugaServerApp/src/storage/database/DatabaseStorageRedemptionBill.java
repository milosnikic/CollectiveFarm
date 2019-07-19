/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Manufacturer;
import domain.MeasurementUnit;
import domain.Product;
import domain.ProductCategory;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import domain.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.AbstractStorageRedemptionBill;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author kicnilec
 */
public class DatabaseStorageRedemptionBill extends AbstractStorageRedemptionBill {

    public boolean saveRedemptionBill(RedemptionBill redemptionBill) throws SQLException, Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "INSERT INTO redemption_bill(date,total,user_id,manufacturer_id) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, new java.sql.Date(redemptionBill.getDate().getTime()));
            preparedStatement.setBigDecimal(2, redemptionBill.getTotal());
            preparedStatement.setLong(3, redemptionBill.getUserCreated().getId());
            preparedStatement.setLong(4, redemptionBill.getManufacturer().getId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Long id = new Long(0);
            if (rs.next()) {
                id = rs.getLong(1);
                redemptionBill.setId(id);
            } else {
                throw new Exception("Invalid id!");
            }

            query = "INSERT INTO redemption_bill_item (redemption_bill_id,item_no,amount,quantity,price,category,product_id) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            for (Object i : redemptionBill.getItems()) {
                RedemptionBillItem item = (RedemptionBillItem) i;
                preparedStatement1.setLong(1, id);
                preparedStatement1.setInt(2, item.getItemNO());
                preparedStatement1.setBigDecimal(3, item.getAmount());
                preparedStatement1.setBigDecimal(4, item.getQuantity());
                preparedStatement1.setBigDecimal(5, item.getPrice());
                preparedStatement1.setString(6, String.valueOf(item.getCategory()));
                preparedStatement1.setLong(7, item.getProduct().getId());
                preparedStatement1.executeUpdate();
            }
            connection.commit();
            preparedStatement.close();
            preparedStatement1.close();
            rs.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            if (connection != null) {
                connection.rollback();
            }
        }
        return false;
    }

    public LinkedList<RedemptionBill> getAllRedemptionBills() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        LinkedList<RedemptionBill> bills = new LinkedList<>();
        LinkedList<RedemptionBillItem> items = new LinkedList<>();
        try {
            String query = "SELECT * FROM redemption_bill rb JOIN USER ON user.id = rb.user_id JOIN manufacturer ON manufacturer.id = rb.manufacturer_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Date date = resultSet.getDate("date");
                BigDecimal amount = resultSet.getBigDecimal("total");
                //Sada uzimamo informacije za korisnika
                Long user_id = resultSet.getLong("user_id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(user_id, firstname, lastname, username, password, email);
                //Sada za proizvodjaca
                Long manufacturer_id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String place = resultSet.getString("place");
                Manufacturer manufacturer = new Manufacturer(manufacturer_id, name, address, place);
                query = "SELECT * FROM redemption_bill_item JOIN product ON product.id = redemption_bill_item.product_id WHERE redemption_bill_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int no = rs.getInt("item_no");
                    BigDecimal am = rs.getBigDecimal("amount");
                    BigDecimal quantity = rs.getBigDecimal("quantity");
                    BigDecimal price = rs.getBigDecimal("price");
                    ProductCategory category = ProductCategory.valueOf(rs.getString("category"));
                    Long productId = rs.getLong("product.id");
                    String productName = rs.getString("product.name");
                    String description = rs.getString("product.description");
                    BigDecimal p = rs.getBigDecimal("product.price");
                    Long p_user_id = rs.getLong("product.user_id");
                    User p_user = DatabaseStorageUser.getUserById(p_user_id);
                    ProductCategory p_category = ProductCategory.valueOf(rs.getString("product.category"));
                    MeasurementUnit measurementUnit = MeasurementUnit.valueOf(rs.getString("product.measurement_unit"));
                    Product product = new Product(productId, productName, description, p, measurementUnit, p_user, p_category);
                    //Potrebno je promeniti model tako da proizvod nije kreiran od strane proizvodjaca, vec da nezavisno postoji i 
                    //zatim sve promeniti u kodu!
                    items.add(new RedemptionBillItem(no, quantity, am, price, product, category));
                }
                bills.add(new RedemptionBill(id, date, amount, items, user, manufacturer));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bills;
    }

    public LinkedList<RedemptionBillItem> getRedemptionBillItems(RedemptionBill rb) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        LinkedList<RedemptionBillItem> items = new LinkedList<>();
        try {
            String query = "SELECT * FROM redemption_bill_item JOIN product on product.id = redemption_bill_item.product_id WHERE redemption_bill_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, rb.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("item_no");
                BigDecimal am = rs.getBigDecimal("amount");
                BigDecimal quantity = rs.getBigDecimal("quantity");
                BigDecimal price = rs.getBigDecimal("price");
                ProductCategory category = ProductCategory.valueOf(rs.getString("category"));
                Long productId = rs.getLong("product.id");
                String productName = rs.getString("product.name");
                String description = rs.getString("product.description");
                BigDecimal p = rs.getBigDecimal("product.price");
                Long p_user_id = rs.getLong("product.user_id");
                User p_user = DatabaseStorageUser.getUserById(p_user_id);
                ProductCategory p_category = ProductCategory.valueOf(rs.getString("product.category"));
                MeasurementUnit measurementUnit = MeasurementUnit.valueOf(rs.getString("product.measurement_unit"));
                Product product = new Product(productId, productName, description, p, measurementUnit, p_user, p_category);
                //Potrebno je promeniti model tako da proizvod nije kreiran od strane proizvodjaca, vec da nezavisno postoji i 
                //zatim sve promeniti u kodu!
                items.add(new RedemptionBillItem(no, quantity, am, price, product, category));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public LinkedList<RedemptionBillItem> getStorageInfo() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        LinkedList<RedemptionBillItem> items = new LinkedList<>();
        try {
            String query = "SELECT NAME,description,p.price,p.category,p.measurement_unit,SUM(quantity) AS quantity FROM product p JOIN redemption_bill_item rb  ON rb.product_id = p.id\n"
                    + "GROUP BY NAME,description,p.category \n"
                    + "ORDER BY NAME,description,p.category ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int no = 1;
            while (resultSet.next()) {
                String productName = resultSet.getString("p.name");
                String productDescription = resultSet.getString("p.description");
                BigDecimal productPrice = resultSet.getBigDecimal("p.price");
                ProductCategory productCategory = ProductCategory.valueOf(resultSet.getString("p.category"));
                MeasurementUnit measurementUnit = MeasurementUnit.valueOf(resultSet.getString("p.measurement_unit"));
                BigDecimal quantity = resultSet.getBigDecimal("quantity");
                Product p = new Product(productName, productDescription, productPrice, measurementUnit, null, productCategory);
                if (!items.isEmpty()) {
                    no++;
                }
                RedemptionBillItem item = new RedemptionBillItem(no, quantity, null, productPrice, p, productCategory);
                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
}
