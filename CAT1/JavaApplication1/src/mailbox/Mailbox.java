/*
 * Mailbox defines a Critical Conditional Region
 * sendMessage must wait when the mailbox is isFull
 * receiveMessage must wait if the mailbox is empty
 * When a thread finishes its execution, unblock others waiting
 */

package mailbox;

import java.util.ArrayList;

public class Mailbox {
    private ArrayList<String> buffer = new ArrayList<>(4);

    public synchronized void sendMessage(String msg){
        while (buffer.size() == 4) {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        buffer.add(msg);
        notifyAll();
    }

    public synchronized String receiveMessage(){
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        notifyAll();
        return buffer.remove(0);
    }
}
