package org.group7.model;
import org.group7.model.board.Board;
import org.group7.model.entities.piece.Piece;
import org.group7.model.board.IMoveHandler;
import org.group7.model.board.Tile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class TileTest {

    int someNumber;
    Tile tile;
    Color[] colorArray = new Color[4];
    IMoveHandler handler;

    @BeforeEach
    void setupBeforeEach(){
        colorArray[0] = Color.RED;
        colorArray[1] = Color.GREEN;
        colorArray[2] = Color.BLUE;
        colorArray[3] = Color.YELLOW;
        someNumber = 0;
        tile = new Tile(someNumber);
    }

    @AfterEach
    void tearDownAfterEach(){
        tile = null;
        handler = null;
    }

    @Test
    void propertiesOfTileInitialized() {
        assertEquals(tile.getIndex(), someNumber);
        assertTrue(tile.isEmpty());
    }

    @Test
    void insertPieceMakesTileNotEmpty() {
        handler = new Board(colorArray);
        assertTrue(tile.isEmpty());
        tile.insertPiece(new Piece(Color.red, handler));
        assertFalse(tile.isEmpty());
    }

    @Test
    void getPieceReturnsSamePieceInsertedPiece() {
        handler = new Board(colorArray);
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        assertEquals(tile.getEntity(), piece);
    }

    @Test
    void removePieceWillRemovePiece() {
        handler = new Board(colorArray);
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        tile.removeEntity();
        assertTrue(tile.isEmpty());
    }

}

