package colours;

// Painter - thread that changes the botton colourswith the folling sequence:
//  First, paint the button black and wait a random time between 2 and 4 secs. 
//  Then, paint the buttong with a new random colour between red, green, blue 
//        or yellow and wait between 3 and 5 secs 
//  Start again

import colours.Palette;
import java.awt.Color;
import javax.swing.JButton;

public class Painter extends Thread {

    private JButton b;
    private Palette p;
    private Color black = Color.black;
    private Gateway gateway;
    
    public Painter(JButton b, Palette p, Gateway gateway){
        this.b=b;
        this.p=p;
        this.gateway=gateway;
        start();        //Start the thread as soon as it is created
    }
    public void run(){
          while (true){
            b.setBackground(black);             //Paint button black
            try {
                sleep(500 + (int)(1500*Math.random()));  //wait btw 0.5 & 2 secs
            } catch (InterruptedException e){}
            gateway.look();            //Check if it needs to stop
            Color c=p.newColour();     //Get a colour from the palette
            b.setBackground(c);        //Paint the button
            try {
                sleep(1000 + (int)(2000*Math.random()));  //Wait btw 1 & 3 secs
            } catch (InterruptedException e){}
            gateway.look();             //Check if it needs to stop
            p.freeColour(c);           // Make the colour available
        }
    }
}