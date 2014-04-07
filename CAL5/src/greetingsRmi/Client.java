package greetingsRmi;

import java.rmi.*;
import java.io.*;

public class Client {
    public static void main(String args[]) {
        String response = "";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input your name:");
            String name = input.readLine();
            InterfaceGreetings obj = (InterfaceGreetings) Naming.lookup("//127.0.0.1/ObjetGreetings");
            response = obj.greet(name);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Excepction : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

