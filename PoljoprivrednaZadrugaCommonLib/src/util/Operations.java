/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author kicnilec
 */
public interface Operations {
    //USER
    public static final int OPERATION_LOGIN = 1;
    public static final int OPERATION_FIND_USER_BY_USERNAME = 14;
    public static final int OPERATION_ADD_USER = 15;
    public static final int OPERATION_GET_ALL_USERS = 16;
    
    //MANUFACTURER
    public static final int OPERATION_GET_ALL_MANUFACTURES = 2;
    public static final int OPERATION_ADD_MANUFACTURER = 3;
    public static final int OPERATION_UPDATE_MANUFACTURER = 4;
    public static final int OPERATION_DELETE_MANUFACTURER = 5;
    
    //PRODUCT
    public static final int OPERATION_ADD_PRODUCT = 6;
    public static final int OPERATION_UPDATE_PRODUCT = 7;
    public static final int OPERATION_DELETE_PRODUCT = 8;
    public static final int OPERATION_GET_ALL_PRODUCTS = 9;
    
    //REDEMPTION BILL
    public static final int OPERATION_SAVE_REDEMPTION_BILL = 10;
    public static final int OPERATION_GET_ALL_REDEMPTION_BILLS = 11;
    public static final int OPERATION_GET_STORAGE_INFO = 12;
    public static final int OPERATION_GET_REDEMPTION_BILL_ITEMS = 13;
    public static final int OPERATION_UPDATE_REDEMPTION_BILL = 17;
    
}
