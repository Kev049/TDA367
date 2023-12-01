package org.group7.view;


import org.group7.model.Entity;
import org.group7.model.Piece;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintableEntityFactory{ //TODO: Ta bort eller gör något med denna klassen
    private static Image image;
    public static PaintablePiece makePieceImage(Piece piece) { //skulle kunna ta bort funktionen ovan och göra denna mer generell
        return makePaintedPiece("src/main/resources/blue_player_circle.png", piece);
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

    public static PaintableEntity createPaintableEntity(){
        return null;
    }
}

