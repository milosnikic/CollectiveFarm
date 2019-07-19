/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kicnilec
 */
public class RedemptionBill implements GeneralEntity {

    private Long id;
    private Date date;
    private BigDecimal total;
    private List items;
    private User userCreated;
    private Manufacturer manufacturer;

    public RedemptionBill() {
    }

    public RedemptionBill(Long id, Date date, BigDecimal total, List<RedemptionBillItem> items, User userCreated, Manufacturer manufacturer) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.items = items;
        this.userCreated = userCreated;
        this.manufacturer = manufacturer;
    }

    public RedemptionBill(Date date, BigDecimal total, List items, User userCreated, Manufacturer manufacturer) {
        this.date = date;
        this.total = total;
        this.items = items;
        this.userCreated = userCreated;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List getItems() {
        return items;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return id + " " + date + " " + total + " " + userCreated.getId() + " " + manufacturer.getId();
    }

    @Override
    public String getTableName() {
        return "redemption_bill";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet resultSet) throws Exception {
        List<GeneralEntity> list = new LinkedList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Date date = resultSet.getDate("date");
            BigDecimal total = resultSet.getBigDecimal("total");

            //Sada uzimamo informacije za korisnika
            Long user_id = resultSet.getLong("user_id");
            User user = new User();
            user.setId(user_id);

            //Sada za proizvodjaca
            Long manufacturer_id = resultSet.getLong("manufacturer_id");
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(manufacturer_id);

            list.add(new RedemptionBill(id, date, total, new LinkedList<RedemptionBillItem>(), user, manufacturer));
        }
        return list;
    }

    @Override
    public String getColumnsForInsert() {
        return "date, total, user_id, manufacturer_id";
    }

    @Override
    public String getValuesForInsert() {
        return "'" + new java.sql.Date(date.getTime()) + "', '" + total + "', '" + userCreated.getId() + "', '" + manufacturer.getId() + "'";
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
                Date date = resultSet.getDate("date");
                System.out.println("DATUM " +date);
                BigDecimal total = resultSet.getBigDecimal("total");

                //Sada uzimamo informacije za korisnika
                Long user_id = resultSet.getLong("user_id");
                User user = new User();
                user.setId(user_id);

                //Sada za proizvodjaca
                Long manufacturer_id = resultSet.getLong("manufacturer_id");
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(manufacturer_id);
                return new RedemptionBill(id, date, total, new LinkedList<RedemptionBillItem>(), user, manufacturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValuesForUpdate() {
        return "date='" + new java.sql.Date(date.getTime()) + "', total=" + total + ", user_id=" + userCreated.getId() + ", manufacturer_id=" + manufacturer.getId();
    }

    public void setItems(LinkedList<GeneralEntity> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public Long getLastId(ResultSet resultSet) {
        Long id = null;
        try {
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
