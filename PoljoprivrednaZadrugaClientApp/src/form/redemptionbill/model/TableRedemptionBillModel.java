/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.redemptionbill.model;

import domain.RedemptionBill;
import domain.RedemptionBillItem;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kicnilec
 */
public class TableRedemptionBillModel extends AbstractTableModel {

    private String[] header = {"ID", "Date", "Total Amount", "User", "Manufacturer"};
    private LinkedList<RedemptionBill> items;

    public TableRedemptionBillModel(LinkedList<RedemptionBill> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RedemptionBill redemptionBill = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return redemptionBill.getId();
            case 1:
                return redemptionBill.getDate();
            case 2:
                return redemptionBill.getTotal();
            case 3:
                return redemptionBill.getUserCreated();
            case 4:
                return redemptionBill.getManufacturer();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    public LinkedList<RedemptionBill> getItems() {
        return items;
    }

}
