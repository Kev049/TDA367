package org.group7.model;

public class PieceFactory {

    public static Piece createPiece(Colour colour){
        return new Piece(colour);
    }

}
