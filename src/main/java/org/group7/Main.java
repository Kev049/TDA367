package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.model.*;
import org.group7.view.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int TOTAL_AMOUNT_FIELD_TILES = 40;
    private static final int TOTAL_AMOUNT_BASES = 4;
    private static final int TOTAL_AMOUNT_GOAL_TILES = 16;
    private static final int TOTAL_AMOUNT_PIECES = 16;
    private static BoardPanel boardPanel;
    private static DrawPanel drawPanel;
    private static List<PaintableTile> paintableFieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
    private static List<PaintableTile> paintableGoalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);
    private static List<PaintableBase> paintableBases = new ArrayList<>(TOTAL_AMOUNT_BASES);
    private static List<Tile> fieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
    private static List<Base> bases = new ArrayList<>(TOTAL_AMOUNT_BASES);
    private static List<Tile> goalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);
    private static List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);

    public static void main(String[] args) {
        //Model instances
        Board board = new Board();
        Game game = new Game();

        //View instances
        fieldTiles = board.getFieldTiles();
        bases = board.getBases();
        goalTiles = board.getGoalTiles();
        /*
        for (int i = 0; i < TOTAL_AMOUNT_PIECES; i++){
            PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(pieces.get(i));
            paintablePieces.add(paintablePiece);
        }
         */

        for(int i = 0; i < TOTAL_AMOUNT_FIELD_TILES; i++){
            PaintableTile paintableTile = TileFactory.createTile(fieldTiles.get(i));
            paintableFieldTiles.add(paintableTile);
        }

        for(Base base : bases){
            PaintableBase paintableBase = new PaintableBase(base);
            paintableBases.add(paintableBase);
        }

        for(int i = 0; i < TOTAL_AMOUNT_GOAL_TILES; i++){
            PaintableTile paintableTile = TileFactory.createTile(goalTiles.get(i));
            paintableGoalTiles.add(paintableTile);
        }

        GameController gameController = new GameController(game);
        List<JButton> buttons = gameController.getListOfButtons();

        boardPanel = new BoardPanel(paintableFieldTiles, paintableBases, paintableGoalTiles);
        drawPanel = new DrawPanel(boardPanel, buttons);

        //Controller
        BoardController boardController = new BoardController(paintableFieldTiles, paintableBases, game, board, boardPanel);

        new GameWindow("TurboFia", drawPanel, boardPanel, game);
    }
}