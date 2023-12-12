package org.group7.model;

import java.awt.*;

public class PieceFactory { //TODO används inte, ta bort eller implementera färdigt

    public static Piece createPiece(Color color, IMoveHandler handler){
        return new Piece(color, handler);
    }

}
