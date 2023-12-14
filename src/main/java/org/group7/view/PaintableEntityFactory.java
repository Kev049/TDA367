package org.group7.view;


import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.entities.powerups.PowerUp;
import org.group7.view.paintables.PaintablePiece;
import org.group7.view.paintables.PaintablePowerUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PaintableEntityFactory {
    private static BufferedImage image;

    public PaintableEntityFactory(){
    }

    public PaintablePiece makePieceImage(Piece piece) {
        String color = null;
        if (piece.getColor().equals(Color.RED)) color = "red";
        else if (piece.getColor().equals(Color.GREEN)) color = "green";
        else if (piece.getColor().equals(Color.YELLOW)) color = "yellow";
        else if (piece.getColor().equals(Color.BLUE)) color = "blue";    //TODO för att vara tydlig kan detta vara en else-if
        return makePaintedPiece("player/" + color + "_player_circle.png", piece);
    }

    private PaintablePiece makePaintedPiece(String s, Piece piece) {
        try {
            // This allows the resource to be compiled into the jar file
            URL path = PaintableEntityFactory.class.getClassLoader().getResource(s);
            image = ImageIO.read(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new PaintablePiece(image, piece);
    }

    private Image getPowerUpImage(PowerUp powerUp) {
        String powerUpImagePath = ("powerups/" + powerUp.getPowerUpName() + "_icon.png");
        try {
            URL path = PaintableEntityFactory.class.getClassLoader().getResource(powerUpImagePath);
            image = ImageIO.read(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public PaintablePowerUp makePaintedPowerUp(PowerUp powerUp) {
        return new PaintablePowerUp(getPowerUpImage(powerUp), powerUp);
    }
}

