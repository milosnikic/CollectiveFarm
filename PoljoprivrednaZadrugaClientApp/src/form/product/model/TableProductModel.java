/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.product.model;

import domain.Manufacturer;
import domain.MeasurementUnit;
import domain.Product;
import domain.ProductCategory;
import domain.User;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kicnilec
 */
public class TableProductModel extends AbstractTableModel {

    private List<Product> products;
    private String[] header = new String[]{"ID", "Name", "Description", "Price", "Measurement Unit", "Category", "User"};

    public TableProductModel(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getDescription();
            case 3:
                return product.getPrice();
            case 4:
                return product.getMeasurementUnit();
            case 5:
                return product.getCategory();
            case 6:
                return product.getSavedUser();
            default:
                return "N/A";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                return false;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                product.setId((Long) aValue);
                break;
            case 1:
                product.setName(aValue.toString());
                break;
            case 2:
                product.setDescription(aValue.toString());
                break;
            case 3:
                product.setMeasurementUnit(MeasurementUnit.valueOf(aValue.toString()));
                break;
            case 4:
                product.setPrice(new BigDecimal(aValue.toString()));
                break;
            case 5:
                product.setCategory(ProductCategory.valueOf(aValue.toString()));
                break;
            case 6:
                product.setSavedUser((User) aValue);
                break;
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        fireTableDataChanged();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        fireTableDataChanged();
    }

    public List<Product> getProducts() {
        return products;
    }
}
