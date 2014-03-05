/*
 * This program runs four writers and one reader
 * that communicate through a mailbox
 * It must be checked that no messages are lost or multiple reads
 */
package mailbox;
public class RunMailbox1 {
    public static void main(String[] s){
        Mailbox mailboxX = new Mailbox();
        Writer pedro = new Writer("Pedro ",5,mailboxX);
        Writer juan = new Writer("Juan ",4,mailboxX);
        Writer antonio = new Writer("Antonio ",6,mailboxX);
        Writer luis = new Writer("Luis ",7,mailboxX);
        Reader jose = new Reader(22,mailboxX);
    }
}
