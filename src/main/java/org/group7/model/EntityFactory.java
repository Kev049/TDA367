package org.group7.model;

import org.group7.model.PowerUps.BasePowerUp;
import org.group7.model.PowerUps.LaserPowerUp;
import org.group7.model.PowerUps.LightningPowerUp;
import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public class EntityFactory {
    public static Piece createPiece(Color color, IMoveHandler handler){
        return new Piece(color, handler);
    }

    public static BasePowerUp createBasePowerUp(IBasePowerUpHandler powerUpHandler){
        return new BasePowerUp(powerUpHandler);
    }

    public static LaserPowerUp createLaserPowerUp(ILaserPowerUpHandler powerUpHandler){
        return new LaserPowerUp(powerUpHandler);
    }

    public static LightningPowerUp createLightningPowerUp(ILightningPowerUpHandler powerUpHandler){
        return new LightningPowerUp(powerUpHandler);
    }
}
