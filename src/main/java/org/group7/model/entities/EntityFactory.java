package org.group7.model.entities;

import org.group7.model.entities.piece.Piece;
import org.group7.model.entities.powerups.BasePowerUp;
import org.group7.model.entities.powerups.LaserPowerUp;
import org.group7.model.entities.powerups.LightningPowerUp;
import org.group7.model.entities.powerups.handlers.IBasePowerUpHandler;
import org.group7.model.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.entities.powerups.handlers.ILightningPowerUpHandler;
import org.group7.model.board.IMoveHandler;

import java.awt.*;

/**
 * The EntityFactory class is a factory class that creates entities (pieces and different power-ups).
 */
public class EntityFactory {

    /**
     * The createPiece function takes in a color and a move handler as parameters and creates a piece
     * @param color The parameter "color" is of type "Color".
     * @param handler The parameter "handler" is of type "IMoveHandler".
     * @return the created piece.
     */
    public static Piece createPiece(Color color, IMoveHandler handler) {
        return new Piece(color, handler);
    }

    /**
     * The createBasePowerUp function takes in a base power-up handler as a parameter and creates a base power-up.
     * @param powerUpHandler The parameter "powerUpHandler" is of type "IBasePowerUpHandler".
     * @return the created base power-up.
     */
    public static BasePowerUp createBasePowerUp(IBasePowerUpHandler powerUpHandler) {
        return new BasePowerUp(powerUpHandler);
    }

    /**
     * The createLaserPowerUp function takes in a laser power-up handler as a parameter and creates a laser power-up.
     * @param powerUpHandler The parameter "powerUpHandler" is of type "ILaserPowerUpHandler".
     * @return the created laser power-up.
     */
    public static LaserPowerUp createLaserPowerUp(ILaserPowerUpHandler powerUpHandler) {
        return new LaserPowerUp(powerUpHandler);
    }

    /**
     * The createLightningPowerUp function takes in a lightning power-up handler as a parameter and creates a lightning power-up.
     * @param powerUpHandler The parameter "powerUpHandler" is of type "ILightningPowerUpHandler".
     * @return the created lightning power-up.
     */
    public static LightningPowerUp createLightningPowerUp(ILightningPowerUpHandler powerUpHandler) {
        return new LightningPowerUp(powerUpHandler);
    }
}
