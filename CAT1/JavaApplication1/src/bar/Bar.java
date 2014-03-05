/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bar;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author mario
 */
public class Bar {

    private ArrayList<Person> room;
    private int maxRoom;
    private Door door;
    private JTextField output;
    
    public Bar(int maxRoom, Door door, JTextField textField) {
        this.room = new ArrayList<>(maxRoom);
        this.maxRoom = maxRoom;
        this.door = door;
        this.output = textField;
    }

    public synchronized void enter(Person person){
        door.check();
        while(room.size() == maxRoom){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        door.check();
        room.add(person);
        update(output);
    }

    public synchronized void leave(Person person){
        door.check();
        room.remove(person);
        update(output);
        notifyAll();
    }

    public synchronized void check(){
        door.check();
    }
    
    public synchronized void update(JTextField output){
        String text="";
        for (int i = 0; i < room.size(); i++) {
            text+=Integer.toString(room.get(i).getPersonId()) + " ";
        }
        output.setText(text);
        //System.out.println(room.size());
    }
}

