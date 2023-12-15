package org.group7.model.board;

import org.group7.model.board.entities.Entity;
import org.group7.model.board.entities.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;
    Color[] colorArray = new Color[4];;

    @BeforeAll
    void beforeAll(){
        colorArray[0] = Color.RED;
        colorArray[1] = Color.GREEN;
        colorArray[2] = Color.BLUE;
        colorArray[3] = Color.YELLOW;
    }

    @BeforeEach
    void setUp() {
        Board board = new Board(colorArray);
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
        board.addPiece(piece, 0);
        board.returnPieceToBase(piece);
        assertEquals(piece, board.getBaseFromColor(color).getPieces()[3]);
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
        Color color = Color.BLUE;
        Piece piece = board.extractPieceFromBase(color);
        board.addPiece(piece, 10);
        Piece piece1 = board.extractPieceFromBase(color);
        board.addPiece(piece1, 11);
        Piece piece2 = board.extractPieceFromBase(color);
        board.addPiece(piece2, 12);

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
    void pieceNoLongerAtGoalStretchIfBouncesToField() {
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 3);
        board.movePiece(piece,6);
        assertFalse(piece.isAtGoalStretch());
    }

    @Test
    void pieceCanBounceToField(){
        Piece piece = new Piece(Color.RED, board);
        board.addPieceToGoalStretch(piece, 3);
        board.movePiece(piece,6);
        assertEquals(piece, board.getFieldTiles()[39].getEntity());
    }

    @Test
    void pieceCanBeMovedInGoalStretch() {
        Piece piece = new Piece(Color.RED, board);
        int steps = 1;
        int diceRoll = 2;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        board.getGoalTiles().get(steps+diceRoll).getEntity();
    }

    @Test
    void pieceBouncesWhenItMovesFurtherThanGoal() {
        Piece piece = new Piece(Color.RED, board);
        int goalPos = 4;
        int steps = 1;
        int diceRoll = 6;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        assertTrue(piece.getPos() < goalPos);
    }

    @Test
    void pieceDoesntBounceWhenItDoesntMovePastGoal() {
        Piece piece = new Piece(Color.RED, board);
        int goalPos = 4;
        int steps = 1;
        int diceRoll = 2;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        assertTrue(piece.getPos() < goalPos);
    }

    @Test
    void goalRemovesPieceWhenItMovesToGoal() {
        Piece piece = new Piece(Color.RED, board);
        int steps = 1;
        int diceRoll = 3;
        board.addPieceToGoalStretch(piece, steps);
        board.movePiece(piece, diceRoll);
        assertEquals( -1,piece.getPos());
    }

    @Test
    void movePieceWillMovePieceAsMuchAsDiceRoll() {
        int diceRoll = 1;
        Piece piece = new Piece(Color.GREEN, board);
        board.addPiece(piece, 0);
        int piecePos = piece.getPos();
        board.movePiece(piece, diceRoll);
        assertEquals(piecePos + diceRoll, piece.getPos());
    }

    @Test
    void movePieceWillMovePieceToGoalStretchIfItMovesPastLastTile(){
        Piece piece = new Piece(Color.RED, board);
        int lastTileForRed = 39;
        board.addPiece(piece, lastTileForRed);
        board.movePiece(piece, 1);
        assertTrue(piece.isAtGoalStretch());
    }

    @Test
    void pieceWillBeRemovedItIsAddedToGoal(){
        Color color = Color.RED;
        int initialAmountOfRedPieces = board.getPieceAmount(color);
        Piece piece = board.getBaseFromColor(Color.red).removePiece();
        board.addPieceToGoalStretch(piece, 0);
        int goalPos = 4;
        board.movePiece(piece, 4);
        assertEquals(initialAmountOfRedPieces - 1,board.getPieceAmount(color));
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