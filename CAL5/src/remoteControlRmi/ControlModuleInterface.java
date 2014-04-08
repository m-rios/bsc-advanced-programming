/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remoteControlRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mario
 */
public interface ControlModuleInterface extends Remote{
    
    void resume() throws RemoteException;
    void stop() throws RemoteException;
    
}
