/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteControSockets;

import colours.Colours;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mario
 */
public class ServerSockets {

    public static void main(String args[]) {
        ServerSocket server;
        Socket connection;
        DataInputStream input;
        Colours colours = new Colours();
        colours.setVisible(true);
        try {
            server = new ServerSocket(5000); // Create socket Port 5000
            System.out.println("Starting server...");
            while (true) {
                // Wait for a connection 
                connection = server.accept();
                System.out.println("request received");
                
                //parse client request
                input = new DataInputStream(connection.getInputStream());
                String msg = input.readUTF();
                if (msg.equalsIgnoreCase("stop")) {
                    colours.gateway.close();
                } else if (msg.equalsIgnoreCase("resume")) {
                    colours.gateway.open();
                } else {
                    System.out.println("request unkown");
                }
                
                //Close connection 
                connection.close();
            }
        } catch (IOException e) {
            System.out.println("Ups: " + e.toString());
        }
    }

}
