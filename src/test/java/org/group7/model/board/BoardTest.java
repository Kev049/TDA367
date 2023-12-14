package org.group7.model.board;

import org.group7.model.board.entities.Entity;
import org.group7.model.board.entities.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }


    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    void pieceAddedToFieldCanBeFoundInTile() {
        Piece piece = new Piece(Color.RED, board);
        int index = 0;
        board.addPiece(piece, index);
        assertEquals(piece, board.getFieldTiles()[index].getEntity());
    }

    @Test
    void pieceAddedToFieldWillNotBeInGoalStretch() {
        Piece piece = new Piece(Color.RED, board);
        int index = 0;
        board.addPiece(piece, index);
        assertFalse(piece.isAtGoalStretch());
    }

    @Test
    void PieceAddedToGoalStretchWillBeInGoalStretch() {
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 0);
        assertTrue(piece.isAtGoalStretch());
    }

    @Test
    void returnPieceToBaseCannotReturnPieceIfBaseIsFull() {
        Piece piece = new Piece(Color.RED, board);
        board.addPiece(piece, 0);
        board.returnPieceToBase(piece);
        assertNotEquals(piece, board.getBases().get(0).getPieces()[0]);
    }

    @Test
    void returnPieceToBaseWillReturnPieceIfBaseIsNotFull() {
        Color color = Color.RED;
        Piece piece = board.extractPieceFromBase(color);
        board.returnPieceToBase(piece);
        assertEquals(piece, board.getBaseFromColor(color).getPieces()[0]);
    }

    @Test
    void returnPieceToBaseWillReturnToSameColorBase(){
        Color color = Color.BLUE;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 0);
        for(int j = 0; j < 3; j++){
            board.extractPieceFromBase(color);
        }
        board.returnPieceToBase(piece);
        assertEquals(piece, board.getBaseFromColor(color).getPieces()[0]);
    }

    @Test
    void returnPieceToBaseWillNotReturnPieceToBaseOfOtherColor(){

        Color color = Color.BLUE;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 0);
        int piecesInBoards = 0;
        for(Base base : board.getBases()){
            while(!base.isEmpty()){
                base.removePiece();
            }
            piecesInBoards += base.getPieceAmount();
        }
        board.returnPieceToBase(piece);
        assertEquals(board.getBaseFromColor(color).getPieceAmount(), piecesInBoards + 1);
    }
    @Test
    void removeEntitiesFromField() { //kolla med Liam hur denna ska funka

    }

    @Test
    void removePowerUpFromField() {
    }

    @Test
    void extractPieceFromBaseReturnsPieceOfCorrectColor() {
        Color color = Color.RED;
        Piece piece = board.extractPieceFromBase(color);
        assertEquals(color, piece.getColor());
    }

    @Test
    void extractPieceFromBaseRemovesOnePieceFromBase() {
        Color color = Color.RED;
        int piecesInBase = board.getBaseFromColor(color).getPieceAmount();
        board.extractPieceFromBase(color);
        assertEquals(piecesInBase - 1, board.getBaseFromColor(color).getPieceAmount());
    }

    @Test
    void pieceFromBaseToFieldAddsPieceToField() { //TODO: Kolla på denna och avgör om den är för "hårdkodad"
        Color color = Color.RED;
        Entity willBeNull = board.getFieldTiles()[0].getEntity();
        board.pieceFromBaseToField(color, 1);
        Entity entity = board.getFieldTiles()[0].getEntity();
        assertNotEquals(entity, willBeNull);
    }

    @Test
    void pieceNoLongerAtGoalStretchIfMovedToField() {
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 0);
        board.pieceFromBaseToField(Color.RED, 1);
        assertFalse(piece.isAtGoalStretch());
    }

    @Test
    void movePieceInGoalStretch() {
    }

    @Test
    void movePiece() {
    }

    @Test
    void spawnPowerUps() {
    }

    @Test
    void addGoalObserver() {
    }

    @Test
    void getBases() {
    }

    @Test
    void getBaseFromColor() {
    }

    @Test
    void getFieldTiles() {
    }

    @Test
    void getGoalTiles() {
    }

    @Test
    void getPieceAmount() {
    }
}