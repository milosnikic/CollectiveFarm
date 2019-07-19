/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Manufacturer;
import domain.Product;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import domain.User;
import java.util.List;
import so.AbstractGenericOperation;
import so.manufacturer.AddManufacturerSO;
import so.manufacturer.DeleteManufacturerSO;
import so.manufacturer.GetAllManufacturesSO;
import so.manufacturer.UpdateManufacturerSO;
import so.product.AddProductSO;
import so.product.DeleteProductSO;
import so.product.GetAllProductsSO;
import so.product.UpdateProductSO;
import so.redemptionbill.GetAllRedemptionBillsSO;
import so.redemptionbill.GetRedemptionBillItemsSO;
import so.redemptionbill.GetStorageInfoSO;
import so.redemptionbill.SaveRedemptionBillSO;
import so.redemptionbill.UpdateRedemptionBillSO;
import so.user.AddUserSO;
import so.user.FindUserByUsernameSO;
import so.user.GetAllUsersSO;
import so.user.LoginSO;
import storage.IDatabaseBroker;
import storage.StorageManufacturer;
import storage.StorageProduct;
import storage.StorageRedemptionBill;
import storage.StorageUser;
import storage.database.DatabaseBroker;
import storage.database.DatabaseStorageManufacturer;
import storage.database.DatabaseStorageProduct;
import storage.database.DatabaseStorageRedemptionBill;
import storage.database.DatabaseStorageUser;

/**
 *
 * @author kicnilec
 */
public class Controller {

    private static Controller instance;

//    private final StorageUser storageUser;
//    private final StorageManufacturer storageManufacturer;
//    private final StorageProduct storageProduct;
//    private final StorageRedemptionBill storageRedemptionBill;
//    private final IDatabaseBroker databaseBroker;

    private Controller() {
//        storageUser = new DatabaseStorageUser();
//        storageManufacturer = new DatabaseStorageManufacturer();
//        storageProduct = new DatabaseStorageProduct();
//        storageRedemptionBill = new DatabaseStorageRedemptionBill();
//        databaseBroker = new DatabaseBroker();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User login(String username, String password) throws Exception {
        AbstractGenericOperation so = new LoginSO();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String condition = "username='" + username + "' AND password='" + password + "'";
        so.templateExecute(user, condition);
        return ((LoginSO) so).getUser();
    }

    public List getAllManufactures() throws Exception {
        AbstractGenericOperation so = new GetAllManufacturesSO();
        so.templateExecute(new Manufacturer(), null);
        return ((GetAllManufacturesSO) so).getList();

    }

    public void addManufacturer(Manufacturer manufacturer) throws Exception {
        AbstractGenericOperation so = new AddManufacturerSO();
        so.templateExecute(manufacturer, null);

    }

    public List getAllProducts() throws Exception {
        AbstractGenericOperation so = new GetAllProductsSO();
        so.templateExecute(new Product(), null);
        return ((GetAllProductsSO) so).getList();
    }

    public void addProduct(Product product) throws Exception {
        AbstractGenericOperation so = new AddProductSO();
        so.templateExecute(product, null);
    }

    public void updateProduct(Product product) throws Exception {
        AbstractGenericOperation so = new UpdateProductSO();
        so.templateExecute(product, null);
    }

    public boolean deleteProduct(Long id) throws Exception {
        AbstractGenericOperation so = new DeleteProductSO();
        so.templateExecute(id, null);
        return ((DeleteProductSO) so).isSuccess();
    }

    public void updateManufacturer(Manufacturer manufacturer) throws Exception {
        AbstractGenericOperation so = new UpdateManufacturerSO();
        so.templateExecute(manufacturer, null);
    }

    public boolean deleteManufacturer(Long id) throws Exception {
        AbstractGenericOperation so = new DeleteManufacturerSO();
        so.templateExecute(id, null);
        return ((DeleteManufacturerSO) so).isSuccess();
    }

    public User findUserByUsername(User newUser) throws Exception {
        AbstractGenericOperation so = new FindUserByUsernameSO();
        so.templateExecute(newUser, null);
        return ((FindUserByUsernameSO) so).getUser();
    }

    public void addUser(User newUser) throws Exception {
        AbstractGenericOperation so = new AddUserSO();
        so.templateExecute(newUser, null);
    }

    public List getAllUsers() throws Exception {
        AbstractGenericOperation so = new GetAllUsersSO();
        so.templateExecute(new User(), null);
        return ((GetAllUsersSO) so).getList();
    }

    public boolean saveRedemptionBill(RedemptionBill redemptionBill) throws Exception {
        AbstractGenericOperation so = new SaveRedemptionBillSO();
        so.templateExecute(redemptionBill, null);
        return ((SaveRedemptionBillSO) so).isSuccess();
    }

    public List getAllRedemptionBills() throws Exception {
        AbstractGenericOperation so = new GetAllRedemptionBillsSO();
        so.templateExecute(new RedemptionBill(), null);
        return ((GetAllRedemptionBillsSO) so).getList();
    }

    public List getRedemptionBillItems(RedemptionBill rb) throws Exception {
        AbstractGenericOperation so = new GetRedemptionBillItemsSO();
        so.templateExecute(rb, null);
        return ((GetRedemptionBillItemsSO) so).getList();
    }

    public List getStorageInfo() throws Exception {
        AbstractGenericOperation so = new GetStorageInfoSO();
        so.templateExecute(new RedemptionBillItem(), null);
        return ((GetStorageInfoSO) so).getList();
    }

    public void updateRedemptionBill(RedemptionBill rb) throws Exception {
        AbstractGenericOperation so = new UpdateRedemptionBillSO();
        so.templateExecute(rb, null);
    }
}
