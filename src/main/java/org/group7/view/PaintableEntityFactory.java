package org.group7.view;


import org.group7.model.Piece;
import org.group7.model.PowerUps.PowerUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PaintableEntityFactory{ //TODO: Ta bort eller gör något med denna klassen
    private static Image image;
    public static PaintablePiece makePieceImage(Piece piece) { //skulle kunna ta bort funktionen ovan och göra denna mer generell
        String color;
        if(piece.getColor().equals(Color.RED)) color = "red";
        else if(piece.getColor().equals(Color.GREEN)) color = "green";
        else if(piece.getColor().equals(Color.YELLOW)) color = "yellow";
        else color = "blue";    //TODO för att vara tydlig kan detta vara en else-if
        return makePaintedPiece("src/main/resources/" + color + "_player_circle.png", piece);
    }

    private static PaintablePiece makePaintedPiece(String s, Piece piece) {
        try{
            image = ImageIO.read(new File(s));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return new PaintablePiece(image, piece);
    }

    private static Image getPowerUpImage(PowerUp powerUp){
        String powerUpImagePath = ("src/main/resources/" + powerUp.getPowerUpName() + "_icon.png");
        try{
            image = ImageIO.read(new File(powerUpImagePath));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return image;
    }

    public static PaintablePowerUp makePaintedPowerUp(PowerUp powerUp){
        return new PaintablePowerUp(getPowerUpImage(powerUp), powerUp);
    }
}

