/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphores;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author mario
 */
public class ReaderSemaphore extends Thread {

    private int id;
    private String contentRead;
    private BookSemaphore book;
    private JTextArea output;

    public ReaderSemaphore(int id, BookSemaphore book) {
        this.id = id;
        this.book = book;
        contentRead = "";
        start();
    }

    public ReaderSemaphore(int id, BookSemaphore book, JTextArea output) {
        this.id = id;
        this.book = book;
        contentRead = new String();
        this.output = output;
        start();
    }

    public int getReaderId() {
        return this.id;
    }

    public void run() {
        this.setName("reader " + this.id);
        while (contentRead.length() < 50) {
            try {
                sleep((long) (500 + 1000 * Math.random()));
            } catch (InterruptedException ex) {
                Logger.getLogger(ReaderSemaphore.class.getName()).log(Level.SEVERE, null, ex);
            }
            contentRead = book.read(this);
            output.setText(output.getText() + this.id + ": " + contentRead + "\n");
        }
    }

}
