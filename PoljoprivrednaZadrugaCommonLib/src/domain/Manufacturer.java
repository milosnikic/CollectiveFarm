/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kicnilec
 */
public class Manufacturer implements GeneralEntity {

    private Long id;
    private String name;
    private String address;
    private String place;

    public Manufacturer(Long id, String name, String address, String place) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.place = place;
    }

    public Manufacturer(String name, String address, String place) {
        this.name = name;
        this.address = address;
        this.place = place;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public String getPlace() {
        return place;
    }

    public Manufacturer() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getTableName() {
        return "manufacturer";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet resultSet) throws Exception {
        List<GeneralEntity> list = new LinkedList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String place = resultSet.getString("place");
            Manufacturer manufacturer = new Manufacturer(id, name, address, place);
            list.add(manufacturer);
        }
        return list;
    }

    @Override
    public String getColumnsForInsert() {
        return "name, address, place";
    }

    @Override
    public String getValuesForInsert() {
        return "'" + name + "', '" + address + "', '" + place + "'";
    }

    @Override
    public String getPrimaryKey() {
        return "id = " + id;
    }

    @Override
    public GeneralEntity getObjectFromRs(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String place = resultSet.getString("place");
                Manufacturer manufacturer = new Manufacturer(id, name, address, place);
                return manufacturer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manufacturer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValuesForUpdate() {
        return "name= '" + name + "', address = '" + address + "', place = '" + place + "'";
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public Long getLastId(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
