/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.GeneralEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.IDatabaseBroker;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author kicnilec
 */
public class DatabaseBroker implements IDatabaseBroker {

    @Override
    public List<GeneralEntity> getAll(GeneralEntity entity, String join) {
        List<GeneralEntity> list = null;
        try {
            String query = "SELECT * FROM " + entity.getTableName();
            if (join != null) {
                query += join;
            }
            System.out.println(query);
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public GeneralEntity getById(GeneralEntity entity, String join) {
        GeneralEntity obj = null;
        try {
            String query = "SELECT * FROM " + entity.getTableName();
            if (join != null) {
                query += join;
            }
            query += " WHERE " + entity.getPrimaryKey();
            System.out.println(query);
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            obj = entity.getObjectFromRs(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public List<GeneralEntity> getByCondition(GeneralEntity entity, String join, String condition) {
        List<GeneralEntity> list = null;
        try {
            String query = "SELECT * FROM " + entity.getTableName();
            if (join != null) {
                query += join;
            }
            query += " WHERE " + condition;
            System.out.println(query);
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void insert(GeneralEntity entity) {
        try {
            String query = "INSERT INTO " + entity.getTableName() + "(" + entity.getColumnsForInsert() + ") VALUES (" + entity.getValuesForInsert() + ")";
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GeneralEntity entity) {
        try {
            String query = "UPDATE " + entity.getTableName() + " SET " + entity.getValuesForUpdate() + " WHERE " + entity.getPrimaryKey();
            System.out.println(query);
            Connection conn = DatabaseConnection.getInstance().getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GeneralEntity entity) {
        try {
            String query = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getPrimaryKey();
            System.out.println(query);
            Connection conn = DatabaseConnection.getInstance().getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Long getLastId(GeneralEntity entity) {
        Long id = null;
        try {
            String query = "SELECT " + entity.getPrimaryKeyName() + " FROM " + entity.getTableName() + " ORDER BY " + entity.getPrimaryKeyName() + " DESC LIMIT 1";
            System.out.println(query);
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            id = entity.getLastId(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public List<GeneralEntity> getAll(GeneralEntity entity, String attrs, String join, String condition, String groupBy, String orderBy) {
        List<GeneralEntity> list = null;
        try {
            String query = "SELECT " + attrs + " FROM " + entity.getTableName();
            if (join != null) {
                query += join;
            }
            if (condition != null) {
                query += " WHERE " + condition;
            }
            if (groupBy != null) {
                query += " GROUP BY " + groupBy;
            }
            if (orderBy != null) {
                query += " ORDER BY " + orderBy;
            }
            System.out.println(query);
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
