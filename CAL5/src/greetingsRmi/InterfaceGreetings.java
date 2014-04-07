package greetingsRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceGreetings extends Remote {

    String greet(String nombre) throws RemoteException;
}
