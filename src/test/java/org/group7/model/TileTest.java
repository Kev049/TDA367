package org.group7.model;
//import org.junit.Test;>
import org.group7.model.Board;
import org.group7.model.IMoveHandler;
import org.group7.model.Piece;
import org.group7.model.Tile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TileTest {

    int someNumber;
    Tile tile;

    //TODO: Check that InsertEntity can't insert an entity if tile already has entity

    @BeforeEach
    void setupBeforeEach(){
        someNumber = 0;
        tile = new Tile(someNumber);
    }

    @AfterEach
    void tearDownAfterEach(){
        tile = null;
    }

    @Test
    void propertiesOfTileInitialized() {
        assertEquals(tile.getIndex(), someNumber);
        assertTrue(tile.isEmpty());
    }

    @Test
    void insertPieceMakesTileNotEmpty() {
        IMoveHandler handler = new Board();
        assertTrue(tile.isEmpty());
        tile.insertPiece(new Piece(Color.red, handler));
        assertFalse(tile.isEmpty());
    }

    @Test
    void getPieceReturnsSamePieceInsertedPiece() {
        IMoveHandler handler = new Board();
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        assertEquals(tile.getPiece(), piece);
    }

    @Test
    void removePieceWillRemovePiece() {
        IMoveHandler handler = new Board();
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        assertFalse(tile.isEmpty());
        tile.removePiece();
        assertTrue(tile.isEmpty());
    }

    @Test //behöver vi ha ett test med denna?
    void isEmpty() {

    }
    @Test
    void getPieceColorReturnsSameColorAsPieceColor() {
        Color red = Color.red;
        IMoveHandler handler = new Board();
        Piece redPiece = new Piece(red, handler);
        tile.insertPiece(redPiece);
        assertEquals(tile.getPieceColor(), redPiece.getColor());
    }

    @Test
    void getPieceColorWorksForAllOurColors() {
        List<Color> colorList = Arrays.asList(Color.red, Color.blue, Color.green, Color.yellow);

        for(Color c : colorList) {
            tile.insertPiece(new Piece(c, new Board()));
            assertEquals(c, tile.getPieceColor());
            tile.removePiece();
        }
    }
}
