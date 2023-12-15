package org.group7.model.utilities;

import java.util.Random;

public class Dice {
    private final Random generator;
    private final int highestDieValue = 6;

    public Dice() {
        long seed = System.currentTimeMillis();
        this.generator = new Random(seed);
    }

    public int roll() {
        return this.generator.nextInt(highestDieValue) + 1;
    }


}