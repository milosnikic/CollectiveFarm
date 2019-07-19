/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.redemptionbill.redemptionbillitems.model;

import domain.RedemptionBillItem;
import java.math.BigDecimal;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kicnilec
 */
public class TableRedemptionBillItemsModel extends AbstractTableModel {

    private String[] header = {"No.", "Product Name", "Product Description", "Measurement Unit", "Quantity", "Category", "Price", "Amount"};
    private LinkedList<RedemptionBillItem> items;

    public TableRedemptionBillItemsModel(LinkedList<RedemptionBillItem> items) {
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return false;
            case 4:
                return false;
            case 5:
                return false;
            case 6:
                return false;
            case 7:
                return false;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RedemptionBillItem item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getItemNO();
            case 1:
                return item.getProduct().getName();
            case 2:
                return item.getProduct().getDescription();
            case 3:
                return item.getProduct().getMeasurementUnit();
            case 4:
                return item.getQuantity();
            case 5:
                return item.getProduct().getCategory();
            case 6:
                return item.getPrice();
            case 7:
                return item.getAmount();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    public void addItem(RedemptionBillItem item) {
        items.add(item);
        fireTableDataChanged();
    }

    public LinkedList<RedemptionBillItem> getItems() {
        return items;
    }

    public void updateItem(RedemptionBillItem item, BigDecimal quantity, BigDecimal amount) {
        for (RedemptionBillItem item1 : items) {
            if (item1.getItemNO() == item.getItemNO()) {
                item1.setAmount(item1.getAmount().add(amount));
                item1.setQuantity(item1.getQuantity().add(quantity));
                break;
            }
        }
        fireTableDataChanged();
    }

    public void deleteItem(RedemptionBillItem item) {
        items.remove(item);
        int i = 1;;
        for (RedemptionBillItem item1 : items) {
            item1.setItemNO(i);
            i++;
        }
        fireTableDataChanged();
    }

}
