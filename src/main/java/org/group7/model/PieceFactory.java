package org.group7.model;

import java.awt.*;

//Vad används den här klassen till? Tror inte den behövs.

public class PieceFactory {

    public static Piece createPiece(Color color){
        return new Piece(color);
    }

}
