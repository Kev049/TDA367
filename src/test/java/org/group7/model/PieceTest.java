package org.group7.model;

import org.group7.model.board.Board;
import org.group7.model.board.IMoveHandler;
import org.group7.model.board.entities.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece piece;
    IMoveHandler handler;
    Color color;

    @BeforeEach
    void setUp() {
        IMoveHandler handler = new Board();
        color = Color.RED;
        piece = new Piece(color, handler);
    }

    @AfterEach
    void tearDown() {
        piece = null;
        handler = null;
    }

    @Test
    void getColorReturnsTheCorrectColor() {assertEquals(piece.getColor(), color);}

    @Test
    void getColorWorksForAllOurColors() {
        List<Color> colorList = Arrays.asList(Color.red, Color.blue, Color.green, Color.yellow);

        for(Color c : colorList) {
            piece = new Piece(c, handler);
            assertEquals(c, piece.getColor());
        }
    }

    @Test
    void getPosGivesTheCorrectPosition() {
        piece.setPos(0);
        assertEquals(piece.getPos(),0);
    }

    @Test
    void PushedPieceIsSentBackToBase() {
        handler = new Board();
        piece = new Piece(Color.RED, handler);
        Piece luigi = new Piece(Color.GREEN, handler);
        handler.addPiece(piece,0);
        piece.handleCollision(luigi);
        assertEquals(piece.getPos(), -1);
    }

//    @Test
//    void piecePushesOtherPiece() { //TODO:Get Kevin on the case
//        Piece goomba = new Piece(Color.BLUE, handler);
//        goomba.handleCollision(piece);
//        assertEquals(goomba.getPos(), -1);
//    }

    @Test
    void setPosChangesPosition() {
        piece.setPos(1);
        int prevPos = piece.getPos();
        piece.setPos(2);
        assertNotEquals(prevPos, piece.getPos());
    }

    @Test
    void pieceIsAtGoalStretchIfAddedToGoalStretch() {
        piece.addToGoalStretch();
        assertTrue(piece.isAtGoalStretch());
    }

    
}