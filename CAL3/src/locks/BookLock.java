/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package locks;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author mario
 */
public class BookLock {

    private String content = "";
    private int nWriters = 0;
    private int nReaders = 0;
    JTextArea readersField;
    JTextArea writersField;
    JProgressBar progress;
    private boolean closed = true;
    private ArrayList<ReaderLock> readers = new ArrayList();

    public BookLock(JTextArea readersField, JTextArea writersField, JProgressBar progress) {
        this.writersField = writersField;
        this.readersField = readersField;
        this.progress = progress;
    }

    public synchronized boolean toggle() {
        notifyAll();
        return closed = !closed;
    }

    private synchronized void checkStatus() {
        while (closed) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BookLock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void write(WriterLock writer) {
        
    }

    public String read(ReaderLock reader) {
        
        return this.content;
    }

    private void updateOutput() {
        String readersList = "";
        for (int i = 0; i < readers.size(); i++) {
            readersList += readers.get(i).getReaderId() + "\n";
        }
        readersField.setText(readersList);
    }
}
