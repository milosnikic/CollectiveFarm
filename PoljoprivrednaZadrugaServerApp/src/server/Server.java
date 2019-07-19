/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import config.Configuration;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ClientThread;

/**
 *
 * @author kicnilec
 */
public class Server extends Thread {

    LinkedList<ClientThread> clients = new LinkedList<>();
    ServerSocket serverSocket;

    @Override
    public void run() {
        try {
            int port = Integer.parseInt(Configuration.getInstance().getProperty("port"));
            serverSocket = new ServerSocket(port);
            while (!isInterrupted()) {
                System.out.println("Server is up and running...");
                System.out.println("Waiting for connection!");
                Socket client = serverSocket.accept();
                System.out.println("Client connected!");
                ClientThread clientThread = new ClientThread(client);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stopServer() {
        try {
            for (ClientThread client : clients) {
                client.serverStopped();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
