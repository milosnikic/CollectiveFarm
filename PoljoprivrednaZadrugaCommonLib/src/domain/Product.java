/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kicnilec
 */
public class Product implements GeneralEntity {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private MeasurementUnit measurementUnit;
    private User savedUser;
    private ProductCategory category;

    public Product() {
        this.price = new BigDecimal(0);
    }

    public Product(Long id, String name, String description, BigDecimal price, MeasurementUnit measurementUnit, User savedUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.measurementUnit = measurementUnit;
        this.savedUser = savedUser;
    }

    public Product(String name, String description, BigDecimal price, MeasurementUnit measurementUnit, User savedUser, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.measurementUnit = measurementUnit;
        this.savedUser = savedUser;
        this.category = category;
    }

    public Product(Long id, String name, String description, BigDecimal price, MeasurementUnit measurementUnit, User savedUser, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.measurementUnit = measurementUnit;
        this.savedUser = savedUser;
        this.category = category;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public User getSavedUser() {
        return savedUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public void setSavedUser(User savedUser) {
        this.savedUser = savedUser;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet resultSet) throws Exception {
        List<GeneralEntity> list = new LinkedList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            BigDecimal price = resultSet.getBigDecimal("price");

            MeasurementUnit measurementUnit = MeasurementUnit.valueOf(resultSet.getString("measurement_unit"));
            ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));

            Long user_id = resultSet.getLong("user_id");
            User savedUser = new User();
            savedUser.setId(user_id);

            Product product = new Product(id, name, description, price, measurementUnit, savedUser, category);
            list.add(product);
        }
        return list;
    }

    @Override
    public String getColumnsForInsert() {
        return "name, description, price, user_id, category, measurement_unit";
    }

    @Override
    public String getValuesForInsert() {
        return "'" + name + "', '" + description + "', '" + price + "', '" + savedUser.getId() + "', '" + category + "', '" + measurementUnit + "'";
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
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");

                MeasurementUnit measurementUnit = MeasurementUnit.valueOf(resultSet.getString("measurement_unit"));
                ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));

                Long user_id = resultSet.getLong("user_id");
                User savedUser = new User();
                savedUser.setId(user_id);

                Product product = new Product(id, name, description, price, measurementUnit, savedUser, category);
                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValuesForUpdate() {
        return "name ='" + name + "', description ='" + description + "', price='" + price
                + "', user_id='" + savedUser.getId() + "', category='"
                + category + "', measurement_unit='" + measurementUnit + "'";
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
