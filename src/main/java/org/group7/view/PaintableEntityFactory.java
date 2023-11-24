package org.group7.view;


import org.group7.model.Entity;
import org.group7.model.Piece;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintableEntityFactory{
    private static Image image;
    public static PaintableEntity makePieceImage(Entity entity) { //skulle kunna ta bort funktionen ovan och göra denna mer generell
        return makePaintedPiece("../../resources/PieceColour.png", entity);
    }
    private static PaintableEntity makePaintedPiece(String s, Entity entity) {
        return null;
    }

    public static PaintableEntity createPaintableEntity(){
        return null;
    }
}
