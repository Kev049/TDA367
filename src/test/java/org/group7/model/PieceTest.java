package org.group7.model;

import org.group7.model.board.Board;
import org.group7.model.board.IMoveHandler;
import org.group7.model.entities.piece.Piece;
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
    Color[] colorArray;

    @BeforeEach
    void setUp() {
        this.colorArray = new Color[4];
        this.colorArray[0] = Color.RED;
        this.colorArray[1] = Color.GREEN;
        this.colorArray[2] = Color.BLUE;
        this.colorArray[3] = Color.YELLOW;
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
        IMoveHandler handler = new Board(colorArray);
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
        IMoveHandler handler = new Board(colorArray);
        piece = new Piece(Color.RED, handler);
        Piece luigi = new Piece(Color.GREEN, handler);
        handler.addPiece(piece,0);
        piece.handleCollision(luigi);
        assertEquals(piece.getPos(), -1);
    }

    @Test
    void setPosChangesPosition() {
        piece.setPos(1);
        int prevPos = piece.getPos();
        piece.setPos(2);
        assertNotEquals(prevPos, piece.getPos());
    }

    @Test
    void isAtGoalStretchReturnsFalseIfNotAtGoalStretch() {
        assertFalse(piece.isAtGoalStretch());
    }

    @Test
    void pieceIsAtGoalStretchIfAddedToGoalStretch() {
        piece.addToGoalStretch();
        assertTrue(piece.isAtGoalStretch());
    }

    @Test
    void removeFromGoalStretchRemovesPieceFromGoalStretch() {
        piece.addToGoalStretch();
        piece.removeFromGoalStretch();
        assertFalse(piece.isAtGoalStretch());
    }
    
}