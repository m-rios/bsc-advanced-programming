/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remoteControlRmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

/**
 *
 * @author mario
 */
public class Client {
    public static void main(String args[]) {
        String response = "";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input your name:");
            String name = input.readLine();
            ControlModuleInterface obj = (ControlModuleInterface) Naming.lookup("//127.0.0.1/ObjetGreetings");
            //response = obj.greet(name);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Excepction : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
