/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remoteControlRmi;

import colours.Colours;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mario
 */
public class RemoteControl extends UnicastRemoteObject 
                           implements ControlModuleInterface {

    Colours colours;
    
    public RemoteControl(Colours colours) throws RemoteException{
        this.colours = colours;
    }
    
    public void resume() throws RemoteException {
        colours.gateway.open();
    }

    public void stop() throws RemoteException {
        colours.gateway.close();
    }
    
}
