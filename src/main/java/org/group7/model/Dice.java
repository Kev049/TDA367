package org.group7.model;

import org.group7.controllers.Observer;

import java.util.Random;

public class Dice implements Observer { // Implementerar Singleton pattern så att ingen klass ska kunna skapa sin egen tärning
    private static Dice instance;       // Detta kan potentiellt leda till problem i genereringen av tal då tärningarna har olika seeds.
    private Random generator;
    //private DiceState state = new OrderedState();
    private Boolean interactable;

    private Dice() {
        long seed = System.currentTimeMillis();     //kanske kan implementera detta som en time klass???
        this.generator = new Random(seed);
        this.interactable = true;
    }

    public static Dice getInstance() {
        if(instance == null){
            instance = new Dice();
        }
        return instance;
    }

    public int roll() {
        if (this.interactable) {
            this.interactable = false;
            return this.generator.nextInt(6) + 1;
        }
        else{
            return 5;
        }
    }

    @Override
    public void update(){
        System.out.println("hi");
    }

    @Override
    public void update(int i) {
        // Empty
    }

    /*
    public void previousState(){
    state.prev(this);
    }

    public void nextState(){
    state.next(this);


     */

}