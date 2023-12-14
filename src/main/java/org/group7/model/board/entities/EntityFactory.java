package org.group7.model.board.entities;

import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.BasePowerUp;
import org.group7.model.board.entities.powerups.LaserPowerUp;
import org.group7.model.board.entities.powerups.LightningPowerUp;
import org.group7.model.board.entities.powerups.handlers.IBasePowerUpHandler;
import org.group7.model.board.entities.powerups.handlers.ILaserPowerUpHandler;
import org.group7.model.board.entities.powerups.handlers.ILightningPowerUpHandler;
import org.group7.model.board.IMoveHandler;

import java.awt.*;

public class EntityFactory {
    public static Piece createPiece(Color color, IMoveHandler handler) {
        return new Piece(color, handler);
    }

    public static BasePowerUp createBasePowerUp(IBasePowerUpHandler powerUpHandler) {
        return new BasePowerUp(powerUpHandler);
    }

    public static LaserPowerUp createLaserPowerUp(ILaserPowerUpHandler powerUpHandler) {
        return new LaserPowerUp(powerUpHandler);
    }

    public static LightningPowerUp createLightningPowerUp(ILightningPowerUpHandler powerUpHandler) {
        return new LightningPowerUp(powerUpHandler);
    }
}
