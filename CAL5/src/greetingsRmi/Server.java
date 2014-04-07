package greetingsRmi;

import java.rmi.*;

public class Server  {

    public static void main(String args[]) {
        try {
            //Instance os the object to register
            Greetinator obj = new Greetinator(); 
            Naming.rebind("//127.0.0.1/ObjetGreetings",obj);
            System.out.println("Objet Greetings has been registered");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
