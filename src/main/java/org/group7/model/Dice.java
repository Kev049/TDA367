package org.group7.model;

import org.group7.controllers.Observer;

import java.util.Random;

public class Dice implements Observer {
    private static Dice instance;
    private Random generator;

    private Dice() {
        long seed = System.currentTimeMillis();     //kanske kan implementera detta som en time klass???
        this.generator = new Random(seed);
    }

    public static Dice getInstance() {
        if(instance == null){
            instance = new Dice();
        }
        return instance;
    }

    public int roll() {
        return this.generator.nextInt(6) + 1;
    }

    @Override
    public void update(){
        System.out.println("hi");
    }

    @Override
    public void update(int i) {
        // Empty
    }

}