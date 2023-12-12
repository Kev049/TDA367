package org.group7.model;

import org.group7.model.PowerUps.BasePowerUp;
import org.group7.model.PowerUps.LightningPowerUp;
import org.group7.model.PowerUps.PowerUp;

public class PowerUpFactory {

    public static PowerUp createPowerUp(String powerUpName, IMoveHandler handler){
        if (powerUpName.equals("Base")) {
            return new BasePowerUp(handler);
        } else if (powerUpName.equals("Lightning")) {
            return new LightningPowerUp(handler);
        } else {
            try {
                throw new Exception("Unknown powerup!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
