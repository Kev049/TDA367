package org.group7.model.game;

import org.group7.model.board.Board;
import org.group7.model.board.entities.Entity;
import org.group7.model.board.entities.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void validateMoveWillNotAllowPieceOfIncorrectColorToMove() {
        Piece piece = new Piece(Color.YELLOW, game.getBoard());
        game.validateMove(piece);
    }

    @Test
    void pieceDoesntMoveIfDieHasNotBeenRolled() {
        Piece piece = new Piece(Color.RED, game.getBoard());
        game.getBoard().addPiece(piece, 0);
        int startingPos = piece.getPos();
        game.movePiece(piece);
        assertEquals(piece.getPos(), startingPos);
    }

    @Test
    void validateBaseMoveReturnsFalseIfCurrentColorIsNotTheSame() {
        Color blue = Color.BLUE;
        assertFalse(game.validateBaseMove(blue));
    }

    @Test
    void moveBasePieceDoesNotMovePieceIfDieHasNotBeenRolled() {
        Color red = Color.RED;
        game.moveBasePiece(red);
        assertEquals(4, game.getBoard().getBaseFromColor(red).getPieceAmount());
    }

    @Test
    void noMovesAvailableIfNoPiecesLeft() {
        Color color = Color.RED;
        Board board = game.getBoard();
        while(board.getBaseFromColor(color).isEmpty()){
            game.getBoard().extractPieceFromBase(Color.RED);
        }
        assertTrue(game.noMovesAvailable());
    }

}