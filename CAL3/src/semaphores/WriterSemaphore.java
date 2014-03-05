/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphores;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class WriterSemaphore extends Thread {

    private int id;
    private BookSemaphore book;

    public WriterSemaphore(int id, BookSemaphore book) {
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
                Logger.getLogger(WriterSemaphore.class.getName()).log(Level.SEVERE, null, ex);
            }
            book.write(this);
        }
    }

}
