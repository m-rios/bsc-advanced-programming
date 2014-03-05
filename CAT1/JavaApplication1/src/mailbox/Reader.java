/*
 * Reader defines threads that read messages from a mailbox 
 * showing them in the screen
 * The mailbox and no of msgs are received as parameters by the constructor
 * Between reading and writing there is a random time between 0.5 and 1 sec.
 */
package mailbox;
public class Reader extends Thread {
    private int noOfMessages;
    private Mailbox myMailbox;

    public Reader(int noOfMessages, Mailbox myMailbox){
        this.noOfMessages=noOfMessages;
        this.myMailbox=myMailbox;
        start();
    }

    @Override
    public void run(){
        for(int i=0; i<noOfMessages; i++){
            try{ sleep((int)(500+500*Math.random()));} catch(InterruptedException e){}
            System.out.println(myMailbox.receiveMessage());
        }
        System.out.println("Total messages read: "+noOfMessages);
    }
}
