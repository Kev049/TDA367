package org.group7.model.game;

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
    void rollDice() {
    }

    @Test
    void roll() {
    }

    @Test
    void validateMoveWillNotAllowPieceOfIncorrectColorToMove() {
        Piece piece = new Piece(Color.YELLOW, game.getBoard());
        game.validateMove(piece);
    }

    @Test
    void move() {
    }

    @Test
    void movePiece() {
    }

    @Test
    void validateBaseMove() {
    }

    @Test
    void moveBasePiece() {
    }

    @Test
    void movePieceOutOfBase() {
    }

    @Test
    void nextPlayer() {
    }

    @Test
    void noMovesAvailable() {
    }

    @Test
    void hasRolledSix() {
    }

    @Test
    void increaseTurnNumber() {
    }

    @Test
    void tryForPowerupSpawn() {
    }

    @Test
    void update() {
    }

    @Test
    void addObserver() {
    }

    @Test
    void testAddObserver() {
    }

    @Test
    void notifyObservers() {
    }

    @Test
    void testNotifyObservers() {
    }
}