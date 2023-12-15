package org.group7.model;
import org.group7.model.board.Board;
import org.group7.model.board.entities.piece.Piece;
import org.group7.model.board.IMoveHandler;
import org.group7.model.board.Tile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class TileTest {

    int someNumber;
    Tile tile;
    Color[] colorArray = new Color[4];
    IMoveHandler handler;

    //TODO: Check that InsertEntity can't insert an entity if tile already has entity

    @BeforeAll
    void doBeforeAll() {
        colorArray[0] = Color.RED;
        colorArray[1] = Color.GREEN;
        colorArray[2] = Color.BLUE;
        colorArray[3] = Color.YELLOW;
    }

    @BeforeEach
    void setupBeforeEach(){
        someNumber = 0;
        tile = new Tile(someNumber);
        IMoveHandler handler = new Board(colorArray);
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
        IMoveHandler handler = new Board(colorArray);
        assertTrue(tile.isEmpty());
        tile.insertPiece(new Piece(Color.red, handler));
        assertFalse(tile.isEmpty());
    }

    @Test
    void getPieceReturnsSamePieceInsertedPiece() {
        IMoveHandler handler = new Board(colorArray);
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        assertEquals(tile.getEntity(), piece);
    }

    @Test
    void removePieceWillRemovePiece() {
        IMoveHandler handler = new Board(colorArray);
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        assertFalse(tile.isEmpty());
        tile.removeEntity();
        assertTrue(tile.isEmpty());
    }

}

