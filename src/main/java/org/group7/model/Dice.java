package org.group7.model;

import java.util.Random;

public class Dice {
    private static Dice instance;
    private Random generator;

    private Dice() {
        long seed = System.currentTimeMillis();     //kanske kan implementera detta som en time klass???
        this.generator = new Random(seed);
    }

    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    public int roll() {
        return this.generator.nextInt(6) + 1;
    }
}