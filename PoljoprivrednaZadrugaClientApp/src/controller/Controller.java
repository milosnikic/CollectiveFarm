/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import domain.Manufacturer;
import domain.Product;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import domain.User;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import transfer.Request;
import transfer.Response;
import util.Operations;
import util.ResponseStatus;

/**
 *
 * @author kicnilec
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User login(String username, String password) throws Exception {
        Request request = new Request();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        request.setData(user);
        request.setOperation(Operations.OPERATION_LOGIN);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (User) response.getData();
        } else {
            System.out.println(response.getError().toString());
            throw (Exception) response.getError();
        }
    }

    public void addManufacturer(Manufacturer manufacturer) throws Exception {
        Request request = new Request();
        request.setData(manufacturer);
        request.setOperation(Operations.OPERATION_ADD_MANUFACTURER);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
        } else {
            System.out.println(response.getError().toString());
            throw (Exception) response.getError();
        }
    }

    public void updateManufacturer(Manufacturer manufacturer) throws Exception {
        Request request = new Request();
        request.setData(manufacturer);
        request.setOperation(Operations.OPERATION_UPDATE_MANUFACTURER);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {

        } else {
            throw (Exception) response.getError();
        }
    }

    public boolean deleteManufacturer(Long id) throws Exception {
        Request request = new Request();
        request.setData(id);
        request.setOperation(Operations.OPERATION_DELETE_MANUFACTURER);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return true;
        } else {
//            throw (Exception) response.getError();
            return false;
        }
    }

    public List getAllManufactures() throws Exception {
        Request request = new Request();
        request.setData(null);
        request.setOperation(Operations.OPERATION_GET_ALL_MANUFACTURES);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (List) response.getData();
        } else {
            System.out.println(response.getError().toString());
            throw (Exception) response.getError();
        }
    }

    public void updateProduct(Product product) throws Exception {
        Request request = new Request();
        request.setData(product);
        request.setOperation(Operations.OPERATION_UPDATE_PRODUCT);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {

        } else {
            throw (Exception) response.getError();
        }
    }

    public void addProduct(Product product) throws Exception {
        Request request = new Request();
        request.setData(product);
        request.setOperation(Operations.OPERATION_ADD_PRODUCT);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
        } else {
            System.out.println(response.getError().toString());
            throw (Exception) response.getError();
        }

    }

    public boolean deleteProduct(Long id) throws Exception {
        Request request = new Request();
        request.setData(id);
        request.setOperation(Operations.OPERATION_DELETE_PRODUCT);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return true;
        } else {
//            throw (Exception) response.getError();
            return false;
        }
    }

    public List<Product> getAllProducts() throws Exception {
        Request request = new Request();
        request.setData(null);
        request.setOperation(Operations.OPERATION_GET_ALL_PRODUCTS);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (List<Product>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public LinkedList<RedemptionBillItem> getStorageInfo() throws Exception {
        Request request = new Request();
        request.setData(null);
        request.setOperation(Operations.OPERATION_GET_STORAGE_INFO);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (LinkedList<RedemptionBillItem>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public boolean saveRedemptionBill(RedemptionBill redemptionBill) throws Exception {
        Request request = new Request();
        request.setData(redemptionBill);
        request.setOperation(Operations.OPERATION_SAVE_REDEMPTION_BILL);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return true;
        } else {
//            throw (Exception) response.getError();
            return false;
        }
    }

    public LinkedList<RedemptionBill> getAllRedemptionBills() throws Exception {
        Request request = new Request();
        request.setData(null);
        request.setOperation(Operations.OPERATION_GET_ALL_REDEMPTION_BILLS);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (LinkedList<RedemptionBill>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public LinkedList<RedemptionBillItem> getRedemptionBillItems(RedemptionBill rb) throws Exception {
        Request request = new Request();
        request.setData(rb);
        request.setOperation(Operations.OPERATION_GET_REDEMPTION_BILL_ITEMS);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (LinkedList<RedemptionBillItem>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public User findUserByUsername(User newUser) throws Exception {
        Request request = new Request();
        request.setData(newUser);
        request.setOperation(Operations.OPERATION_FIND_USER_BY_USERNAME);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (User) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public void addUser(User newUser) throws Exception {
        Request request = new Request();
        request.setData(newUser);
        request.setOperation(Operations.OPERATION_ADD_USER);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {

        } else {
            throw (Exception) response.getError();
        }
    }

    public LinkedList<User> getAllUsers() throws Exception {
        Request request = new Request();
        request.setData(null);
        request.setOperation(Operations.OPERATION_GET_ALL_USERS);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return (LinkedList<User>) response.getData();
        } else {
            throw (Exception) response.getError();
        }
    }

    public boolean updateRedemptionBill(RedemptionBill redemptionBill) throws Exception {
        Request request = new Request();
        request.setData(redemptionBill);
        request.setOperation(Operations.OPERATION_UPDATE_REDEMPTION_BILL);
        Communication.getInstance().sendRequest(request);

        Response response = Communication.getInstance().readResponse();

        if (response.getStatus() == ResponseStatus.OK) {
            return true;
        } else {
            throw (Exception) response.getError();
        }
    }

}
