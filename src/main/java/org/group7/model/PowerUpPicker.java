package org.group7.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpPicker {    //TODO diskutera poängen med denna klassen
    private static PowerUpPicker instance;
    private final Random powerUpGenerator;
    private final List<String> powerUpList;

    private PowerUpPicker() {
        long seed = System.currentTimeMillis() * 31;
        this.powerUpGenerator = new Random(seed);
        this.powerUpList = new ArrayList<>();
        populatePowerUpList();
    }

    private void populatePowerUpList() {
        this.powerUpList.add("Base");
        this.powerUpList.add("Lightning");
    }

    public static PowerUpPicker getInstance() {
        if (instance == null) {
            instance = new PowerUpPicker();
        }
        return instance;
    }

    public String choosePowerUp() {
        int randomPowerUp = powerUpGenerator.nextInt(powerUpList.size());
        return powerUpList.get(randomPowerUp);
    }

}
