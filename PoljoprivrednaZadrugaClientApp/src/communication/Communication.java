/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import config.Configuration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author kicnilec
 */
public class Communication {

    private static Communication instance;
    private Socket socket;

    private Communication() {
        int port = Integer.parseInt(Configuration.getInstance().getProperty("port"));
        System.out.println("Port na : " + port);
        try {
            socket = new Socket("localhost", port);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server is not running!");
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void sendRequest(Request request) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(request);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public Response readResponse() throws IOException, ClassNotFoundException {
        Response res = new Response();
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        res = (Response) input.readObject();
        return res;

    }
}
