package org.group7.model.utilities;

import java.util.Random;

public class Dice { // Implementerar Singleton pattern så att ingen klass ska kunna skapa sin egen tärning
    private static Dice instance;       // Detta kan potentiellt leda till problem i genereringen av tal då tärningarna har olika seeds.
    private final Random generator;
    private final int highestDieValue = 6;

    private Dice() {
        long seed = System.currentTimeMillis();
        this.generator = new Random(seed);
    }

    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    public int roll() {
        return this.generator.nextInt(highestDieValue) + 1;
    }


}