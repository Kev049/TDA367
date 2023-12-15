package org.group7.model.board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GoalStretchTest {

    GoalStretch goalStretch;
    PieceExtractor handler;

    @BeforeEach
    void setUp() {

        handler = new Board();
        goalStretch = new GoalStretch(Color.RED, handler);
    }

    @AfterEach
    void tearDown() {
        handler = null;
        goalStretch = null;
    }

    @Test
    void GoalStretchInitializesWithCorrectColor() {
        assertEquals(Color.RED, goalStretch.getColor());
    }

}