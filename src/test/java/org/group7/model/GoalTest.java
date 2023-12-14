package org.group7.model;

import org.group7.model.board.Board;
import org.group7.model.board.Board.Entities.Piece;
import org.group7.model.board.IMoveHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class GoalTest {

    Goal goal;
    Piece piece;
    Color color;
    IMoveHandler handler;

    @BeforeEach
    void setUp() {
        this.goal = new Goal();
        this.handler = new Board();
        this.piece = new Piece(Color.RED, handler);
    }

    @AfterEach
    void tearDown() {
        this.piece = null;
        this.handler = null;
        this.goal = null;
    }

    @Test
    void removeEntitySetsPiecePosOutOfBounds() { //Denna funkar men för fel anledning
        goal.insertPiece(piece);
        assertEquals(-1, piece.getPos());
    }

//    @Test
//    void

    @Test
    void removeEntity() {
    }
}