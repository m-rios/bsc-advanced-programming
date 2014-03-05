/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;

/**
 *
 * @author mario
 */
public class Person extends Thread {

    private int id;
    private Bar pub;

    public Person(int id, Bar bar) {
        this.id = id;
        this.pub = bar;
        start();
    }

    public int getPersonId(){
        return this.id;
    }

    private void decide() {
        try {
            sleep((int) (200 + 300 * Math.random()));
        } catch (InterruptedException e) {}
    }

    private void takeDrink() {
        try {
            sleep((int) (200 + 300 * Math.random()));
        } catch (InterruptedException e) {}
    }

    @Override
    public void run() {

        //pub.check();
        decide();
        pub.enter(this);
        //System.out.println("Person: " + this.id + " enters");
        //pub.check();
        takeDrink();
        pub.leave(this);
        //System.out.println("Person: " + this.id + " leaves");
    }
}
