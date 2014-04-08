package remoteControlRmi;

import colours.Colours;
import greetingsRmi.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server  {

    public static void main(String args[]) {
        try {
            //Instance os the object to register
            Colours colours = new Colours();
            RemoteControl obj = new RemoteControl(colours); 
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/RemoteControl",obj);
            System.out.println("Objet RemoteControl has been registered");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
