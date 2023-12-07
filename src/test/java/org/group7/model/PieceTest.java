package org.group7.model;

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
        piece = new Piece(color ,handler);
    }

    @AfterEach
    void tearDown() {
        piece = null;
        handler = null;
    }

    @Test
    void getColorReturnsTheCorrectColor() {
        assertEquals(piece.getColor(), color);
    }

    @Test
    void getColorWorksForAllOurColors() {
        List<Color> colorList = Arrays.asList(Color.red, Color.blue, Color.green, Color.yellow);

        for(Color c : colorList) {
            piece = null;
            piece = new Piece(c, handler);
            assertEquals(c, piece.getColor());
        }
    }

    @Test
    void getPosGivesTheCorrectPosition() {
        int num = 0;
        piece.setPos(0);
        assertEquals(piece.getPos(),0);
    }

    @Test
    void handleCollision() {

    }

    @Test
    void setPosChangesPosition() {
        piece.setPos(1);
        int prevPos = piece.getPos();
        piece.setPos(2);
        assertNotEquals(prevPos, piece.getPos());
    }
}