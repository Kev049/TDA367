package org.group7.model;

import org.group7.model.PowerUps.PowerUp;

import java.awt.*;

public class EntityFactory { //TODO varför existerar denna?
    public static Piece createPiece(Color color, IMoveHandler handler){
        return new Piece(color, handler);
    }

    public static PowerUp createPowerUp(String powerUpName, IPowerUpHandler handler){
        return new PowerUp(powerUpName, handler);
    }
}
