/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monitors;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author mario
 */
public class BookMonitor {

    private String content = "";
    private int nWriters = 0;
    private int nReaders = 0;
    JTextArea readersField;
    JTextArea writersField;
    JProgressBar progress;
    private boolean closed = true;
    private ArrayList<ReaderMonitor> readers = new ArrayList();

    public BookMonitor(JTextArea readersField, JTextArea writersField, JProgressBar progress) {
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
                Logger.getLogger(BookMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void attemptToWrite(String id) throws InterruptedException {
        while (nWriters > 0) {
            wait();
        }
        nWriters++;
        writersField.setText(id);
        while (nReaders > 0) {
            wait();
        }
    }

    private void stopWriting() {
        nWriters--;
        if (nWriters == 0) {
            notifyAll();
        }
        writersField.setText("");
        progress.setValue(progress.getValue() + 1);
    }

    public synchronized void write(WriterMonitor writer) {

        checkStatus();
        String id = Integer.toString(writer.getWriterId());
        try {
            attemptToWrite(id);
        } catch (InterruptedException ex) {
        }
        checkStatus();
        content += id;
        stopWriting();
    }

    private synchronized void attemptToRead(ReaderMonitor reader) throws InterruptedException {
        while (nWriters > 0) {
            wait();
        }
        readers.add(reader);
        nReaders++;
    }

    private synchronized void stopReading(ReaderMonitor reader) {
        readers.remove(reader);
        nReaders--;
        if (nReaders == 0) {
            notifyAll();
        }
    }

    public String read(ReaderMonitor reader) {
        checkStatus();
        try {
            attemptToRead(reader);
            checkStatus();
            updateOutput();
            sleep((long) (500 + 1000 * Math.random()));
        } catch (InterruptedException ex) {
            Logger.getLogger(BookMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkStatus();
        stopReading(reader);
        checkStatus();
        updateOutput();
        return this.content;
    }

    private synchronized void updateOutput() {
        String readersList = "";
        for (int i = 0; i < readers.size(); i++) {
            readersList += readers.get(i).getReaderId() + "\n";
        }
        readersField.setText(readersList);
    }
}