package org.group7.model;

import java.util.Random;

public class Dice { // Implementerar Singleton pattern så att ingen klass ska kunna skapa sin egen tärning
    private static Dice instance;       // Detta kan potentiellt leda till problem i genereringen av tal då tärningarna har olika seeds.
    private Random generator;
    //private DiceState state = new OrderedState();

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


}