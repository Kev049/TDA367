package org.group7.model;

import java.awt.*;

public class PieceFactory {

    public static Piece createPiece(Color color){
        return new Piece(color);
    }

}
