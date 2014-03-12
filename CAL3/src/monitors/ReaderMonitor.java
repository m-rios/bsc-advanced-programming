/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitors;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author mario
 */
public class ReaderMonitor extends Thread {

    private int id;
    private String contentRead;
    private BookMonitor book;
    private JTextArea output;

    public ReaderMonitor(int id, BookMonitor book) {
        this.id = id;
        this.book = book;
        contentRead = "";
        start();
    }

    public ReaderMonitor(int id, BookMonitor book, JTextArea output) {
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
                Logger.getLogger(ReaderMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            contentRead = book.read(this);
            output.setText(output.getText() + this.id + ": " + contentRead + "\n");
        }
    }

}
