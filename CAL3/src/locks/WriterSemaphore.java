/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locks;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class WriterLock extends Thread {

    private int id;
    private BookLock book;

    public WriterLock(int id, BookLock book) {
        this.id = id;
        this.book = book;
        start();
    }
    
    public int getWriterId(){
        return this.id;
    }

    public void run() {
        this.setName("writer " + this.id);
        for (int i = 0; i < 5; i++) {
            try {
                sleep((long) (1000 + 1000 * Math.random()));
            } catch (InterruptedException ex) {
                Logger.getLogger(WriterLock.class.getName()).log(Level.SEVERE, null, ex);
            }
            book.write(this);
        }
    }

}
