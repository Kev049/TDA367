package org.group7.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

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
    void insertPieceRemovesPiece() {
        goal.insertPiece(piece);
        assertEquals(piece.getPos(), -1);
    }

    @Test
    void removeEntity() {
    }
}