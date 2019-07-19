/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.Controller;
import domain.GeneralEntity;
import domain.Manufacturer;
import domain.Product;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import domain.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import util.Operations;
import util.ResponseStatus;

/**
 *
 * @author kicnilec
 */
public class ClientThread extends Thread {

    Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object obj = ois.readObject();
                if (obj instanceof Request) {
                    Request request = (Request) obj;
                    Response response = handleRequest(request);
                    sendResponse(socket, response);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public Response handleRequest(Request request) {
        int operation = request.getOperation();
        Response response = new Response();
        switch (operation) {
            case Operations.OPERATION_LOGIN:
                try {
                    User userRequest = (User) request.getData();
                    GeneralEntity user = Controller.getInstance().login(userRequest.getUsername(), userRequest.getPassword());
                    response.setStatus(ResponseStatus.OK);
                    response.setData(user);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
                break;
            case Operations.OPERATION_ADD_USER:
                try {
                    User userRequest = (User) request.getData();
                    Controller.getInstance().addUser(userRequest);
                    response.setStatus(ResponseStatus.OK);
                    response.setData(null);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
                break;
            case Operations.OPERATION_FIND_USER_BY_USERNAME:
                try {
                    User userRequest = (User) request.getData();
                    User user = Controller.getInstance().findUserByUsername(userRequest);
                    if (user != null) {
                        response.setStatus(ResponseStatus.OK);
                        response.setData(user);
                    } else {
                        response.setStatus(ResponseStatus.OK);
                        response.setData(null);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
                break;
            case Operations.OPERATION_GET_ALL_USERS:
                try {
                    LinkedList<User> users = (LinkedList<User>) Controller.getInstance().getAllUsers();
                    response.setStatus(ResponseStatus.OK);
                    response.setData(users);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
                break;
            case Operations.OPERATION_GET_ALL_MANUFACTURES:
                try {
                    LinkedList<GeneralEntity> manufactures = (LinkedList<GeneralEntity>) Controller.getInstance().getAllManufactures();
                    response.setData(manufactures);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;

            case Operations.OPERATION_ADD_MANUFACTURER: {
                try {
                    Manufacturer manufacturer = (Manufacturer) request.getData();
                    Controller.getInstance().addManufacturer(manufacturer);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            }
            case Operations.OPERATION_UPDATE_MANUFACTURER:
                try {
                    Manufacturer manufacturer = (Manufacturer) request.getData();
                    Controller.getInstance().updateManufacturer(manufacturer);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_DELETE_MANUFACTURER:
                try {
                    Long id = (Long) request.getData();
                    Controller.getInstance().deleteManufacturer(id);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_GET_ALL_PRODUCTS:
                try {
                    LinkedList<GeneralEntity> products = (LinkedList<GeneralEntity>) Controller.getInstance().getAllProducts();
                    response.setData(products);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
                break;
            case Operations.OPERATION_ADD_PRODUCT:
                try {
                    Product product = (Product) request.getData();
                    Controller.getInstance().addProduct(product);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_UPDATE_PRODUCT:
                try {
                    Product product = (Product) request.getData();
                    Controller.getInstance().updateProduct(product);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_DELETE_PRODUCT:
                try {
                    Long id = (Long) request.getData();
                    Controller.getInstance().deleteProduct(id);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_GET_ALL_REDEMPTION_BILLS:
                try {
                    LinkedList<GeneralEntity> redemptionBills = (LinkedList<GeneralEntity>) Controller.getInstance().getAllRedemptionBills();
                    response.setData(redemptionBills);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_SAVE_REDEMPTION_BILL:
                try {
                    RedemptionBill redemptionBill = (RedemptionBill) request.getData();
                    Controller.getInstance().saveRedemptionBill(redemptionBill);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            case Operations.OPERATION_GET_REDEMPTION_BILL_ITEMS: {
                try {
                    RedemptionBill redemptionBill = (RedemptionBill) request.getData();
                    LinkedList<RedemptionBillItem> items = (LinkedList<RedemptionBillItem>) Controller.getInstance().getRedemptionBillItems(redemptionBill);
                    response.setData(items);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            }
            case Operations.OPERATION_GET_STORAGE_INFO: {
                try {
                    LinkedList<RedemptionBillItem> items = (LinkedList<RedemptionBillItem>) Controller.getInstance().getStorageInfo();
                    response.setData(items);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
                break;
            }
            case Operations.OPERATION_UPDATE_REDEMPTION_BILL: {
                try {
                    RedemptionBill rb = (RedemptionBill) request.getData();
                    Controller.getInstance().updateRedemptionBill(rb);
                    response.setData(null);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setError(ex);
                    response.setStatus(ResponseStatus.ERROR);
                }
                break;
            }

        }
        return response;
    }

    public void sendResponse(Socket socket, Response response) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(response);
    }

    public void serverStopped() {
        try {
            socket.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
