/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.user.model;

import domain.User;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kicnilec
 */
public class TableUserModel extends AbstractTableModel {

    private String[] header = {"Firstname", "Lastname", "Username", "Email"};
    private LinkedList<User> users = new LinkedList<User>();

    public TableUserModel(LinkedList<User> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getFirstname();
            case 1:
                return user.getLastname();
            case 2:
                return user.getUsername();
            case 3:
                return user.getEmail();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

}
