/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphores;

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
public class BookSemaphore {

    private String content = "";
    private int nWriters = 0;
    private int nReaders = 0;
    private Semaphore mutexR = new Semaphore(1);
    private Semaphore mutexW = new Semaphore(1);
    private Semaphore writerWaiting = new Semaphore(1);
    JTextArea readersField;
    JTextArea writersField;
    JProgressBar progress;
    private boolean closed = true;
    private ArrayList<ReaderSemaphore> readers = new ArrayList();

    public BookSemaphore(JTextArea readersField, JTextArea writersField, JProgressBar progress) {
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
                Logger.getLogger(BookSemaphore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void write(WriterSemaphore writer) {
        try {
            writerWaiting.acquire();   //(avoid MORE readers)
            String id = Integer.toString(writer.getWriterId());
            writersField.setText(id);
            
            mutexW.acquire();       //block if reader reading
            mutexW.release();
            
            content += id;
            writersField.setText("");
            writerWaiting.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(BookSemaphore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String read(ReaderSemaphore reader) {
        try {
            writerWaiting.acquire(); //block if writer awaiting
            writerWaiting.release();

            mutexR.acquire();
            readers.add(reader);
            updateOutput();
            nReaders++;
            if (nReaders == 1) {
                mutexW.acquire(); //block writers
            }
            mutexR.release();

            //read
            mutexR.acquire();
            readers.remove(reader);
            updateOutput();
            nReaders--;
            if (nReaders == 0) {
                mutexW.release();
            }
            mutexR.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(BookSemaphore.class.getName()).log(Level.SEVERE, null, ex);
        }
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
