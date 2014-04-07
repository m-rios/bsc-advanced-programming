package greetingsRmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Greetinator  extends UnicastRemoteObject 
                          implements InterfaceGreetings {
        //Constructor
        public Greetinator() throws RemoteException {}         
        
        // Implementación del metodo remoto
        public String greet(String nombre) throws RemoteException  {   
           return "Good morning " + nombre;
        }

}
