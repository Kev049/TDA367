package org.group7.view;

import org.group7.model.Entity;

public class PaintableEntityFactory{

    public static PaintableEntity makePieceImage(Entity entity) { //skulle kunna ta bort funktionen ovan och göra denna mer generell
        return makePaintedPiece("../../resources/PieceColour.png", entity);
    }

    private static PaintableEntity makePaintedPiece(String s, Entity entity) {
        return null;
    }

    private static PaintableEntity createPaintEntity(){
        return null;
    }
}
