package org.group7.model;

import org.group7.model.PowerUps.BasePowerUp;
import org.group7.model.PowerUps.LightningPowerUp;
import org.group7.model.PowerUps.PowerUp;

public class PowerUpFactory { //TODO ta bort/bestäm vad som ska hända med denna klass, implementerad fel och anvönds inte

    public static PowerUp createPowerUp(String powerUpName, IPowerUpHandler handler){
        if (powerUpName.equals("Base")) {
            return new BasePowerUp(handler);
        }
        else if (powerUpName.equals("Lightning")) {
            return new LightningPowerUp(handler);
        }
        else {
            try {
                throw new Exception("Unknown powerup!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
