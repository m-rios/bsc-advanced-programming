package colours;

import java.awt.Color;

// Palette stores four colours for the painters
public class Palette {
    
    private Color [] colours = new Color[4];
    private boolean[] available = new boolean[4];
    
    public Palette(){
        colours[0]=Color.red;
        colours[1]=Color.blue;
        colours[2]=Color.green;
        colours[3]=Color.yellow;
        for (int i=0;i<4;i++) available[i]=true;
    }
    
    public synchronized Color newColour(){
        while(true){
            int i = (int)(4 * Math.random());    //Choose random colour
            if(available[i]) {
                available[i]=false;
                return colours[i];
            }
        }
    }
    public synchronized void freeColour(Color c){
        for(int i=0;i<4;i++) if(c.equals(colours[i])) available[i]=true;
    }
}
