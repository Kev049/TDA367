package org.group7Test;
//import org.junit.Test;>
import org.group7.model.Board;
import org.group7.model.IMoveHandler;
import org.group7.model.Piece;
import org.group7.model.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TileTest {

    @Test
    void propertiesOfTileInitialized() {
        int someNumber = 0;
        Tile tile = new Tile(someNumber);
        assertEquals(tile.getIndex(), someNumber);
        assertTrue(tile.isEmpty());
    }

    @Test
    void insertPieceMakesTileNotEmpty() {
        int someNumber = 0;
        IMoveHandler handler = new Board();
        Tile tile = new Tile(someNumber);
        assertTrue(tile.isEmpty());
        tile.insertPiece(new Piece(Color.red, handler));
        assertFalse(tile.isEmpty());
    }

    @Test
    void getPieceReturnsSamePieceInsertedPiece() {
        int someNumber = 0;
        IMoveHandler handler = new Board();
        Tile tile = new Tile(someNumber);
        Piece piece = new Piece(Color.red, handler);
        tile.insertPiece(piece);
        assertEquals(tile.getPiece(), piece);
    }

    @Test
    void removePieceWillRemovePiece() {
        int someNumber = 0;
        IMoveHandler handler = new Board();
        Tile tile = new Tile(someNumber);
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
        int someNumber = 0;
        Color red = Color.red;
        IMoveHandler handler = new Board();
        Tile tile = new Tile(someNumber);
        Piece redPiece = new Piece(red, handler);
        assertEquals(tile.getPieceColor(), redPiece.getColor());
    }

    @Test
    void getPieceColorWorksForAllOurColors() {
        int someNumber = 0;
        List<Color> colorList = Arrays.asList(Color.red, Color.blue, Color.green, Color.yellow);
        Tile tile = new Tile(someNumber);

        for(Color c : colorList) {
            tile.insertPiece(new Piece(c, new Board()));
            assertEquals(c, tile.getPieceColor());
            tile.removePiece();
        }
    }
}
