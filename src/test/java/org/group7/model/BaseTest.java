package org.group7.model;

import org.group7.model.board.Base;
import org.group7.model.board.Board;
import org.group7.model.board.entities.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {

    Base base;
    int capacity;

    @AfterEach
    void tearDown() {
        this.base = null;
        capacity = 0;
    }

    @Test
    void baseCanHaveFourPieces() {
        capacity = 4;
        this.base = new Base(capacity, Color.RED, new Board());
        assertEquals(capacity, this.base.getPieceAmount());
    }

    @Test
    void baseIsEmptyAfterRemovingAllPieces() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        for (int i = 0; i < capacity; i++) {
            this.base.removePiece();
        }
        assertTrue(this.base.isEmpty());
    }

    @Test
    void baseIsNotEmptyIfPiecesAreNotRemoved() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        assertFalse(this.base.isEmpty());
    }

    @Test
    void removingOneOfXPiecesFromBaseShouldReturnXMinusOnePieces() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        this.base.removePiece();
        assertEquals(capacity-1, this.base.getPieceAmount());
    }

    @Test
    void removingMorePiecesThanCapacityShouldReturnNull() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        this.base.removePiece();
        this.base.removePiece();
        assertNull(this.base.removePiece());
    }

    @Test
    void addPieceIncreasesPieceAmountByOne() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        this.base.removePiece();
        this.base.addPiece(new Piece(Color.RED, new Board()));
        assertEquals(2, this.base.getPieceAmount());
    }

    @Test
    void addPieceDoesNotIncreasePieceAmountIfBaseIsFull() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        this.base.addPiece(new Piece(Color.RED, new Board()));
        assertEquals(2, this.base.getPieceAmount());
    }

    @Test
    void isEmptyReturnsTrueIfEmpty() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        this.base.removePiece();
        this.base.removePiece();
        assertTrue(this.base.isEmpty());
    }

    @Test
    void isEmptyReturnsFalseIfNotEmpty() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        assertFalse(this.base.isEmpty());
    }

    @Test
    void getPiecesReturnsEmptyArrayIfBaseIsEmpty() {
        capacity = 0;
        this.base = new Base(capacity, Color.RED, new Board());
        assertEquals(this.base.getPieces().length, 0);
    }

    @Test
    void getColorReturnsCorrectColor() {
        Color color = Color.red;
        this.base = new Base(2, color, new Board());
        assertEquals(color, this.base.getColor()); }

    @Test
    void getPieceAmountReturnsCorrect() {
        capacity = 2;
        this.base = new Base(capacity, Color.RED, new Board());
        assertEquals(capacity, this.base.getPieceAmount());
    }

    @Test
    void getPieceAmountReturnsNullIfBaseIsEmpty() {
        capacity = 0;
        this.base = new Base(capacity, Color.RED, new Board());
        assertEquals(capacity, this.base.getPieceAmount());
    }
}