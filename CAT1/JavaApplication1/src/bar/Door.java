/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

/**
 *
 * @author mario
 */
public class Door {

    private boolean closed = true;

    public synchronized void open() {
        closed = false;
        notifyAll();
    }

    public synchronized void close() {
        closed = true;
    }
    
    public synchronized boolean toggle(){
        if (closed) {
            open();
        }else{
            close();
        }
        return closed;
    }
    
    public synchronized void check(){
        while(closed){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
    }
}
