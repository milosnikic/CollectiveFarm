/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.manufacturer.model;

import domain.Manufacturer;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kicnilec
 */
public class TableManufacturerModel extends AbstractTableModel {

    private LinkedList<Manufacturer> manufactures;
    private String[] header = {"ID", "Name", "Address", "Place"};

    public TableManufacturerModel(LinkedList<Manufacturer> manufactures) {
        this.manufactures = manufactures;
    }

    @Override
    public int getRowCount() {
        return manufactures.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Manufacturer manufacturer = manufactures.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return manufacturer.getId();
            case 1:
                return manufacturer.getName();
            case 2:
                return manufacturer.getAddress();
            case 3:
                return manufacturer.getPlace();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    public LinkedList<Manufacturer> getManufactures() {
        return manufactures;
    }

    public void removeManufacturer(int rowSelected) {
        manufactures.remove(rowSelected);
        fireTableDataChanged();
    }

}
