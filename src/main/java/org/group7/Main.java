package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.model.*;
import org.group7.view.*;
//import controller.Game;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.Color;

public class Main {
    private static final int TOTAL_AMOUNT_TILES = 72;
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
        List<Piece> pieces = board.getAllPieces();
        for (int i = 0; i < TOTAL_AMOUNT_PIECES; i++){
            PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(pieces.get(i));
            paintablePieces.add(paintablePiece);
        }

        for(int i = 0; i < TOTAL_AMOUNT_FIELD_TILES; i++){
            PaintableTile paintableTile = TileFactory.createTile(fieldTiles.get(i), null);
            paintableFieldTiles.add(paintableTile);
        }

        for(Base base : bases){
            PaintableBase paintableBase = new PaintableBase(base);
            paintableBases.add(paintableBase);
        }

        for(int i = 0; i < TOTAL_AMOUNT_GOAL_TILES; i++){
            PaintableTile paintableTile = TileFactory.createTile(goalTiles.get(i), null);
            paintableGoalTiles.add(paintableTile);
        }


        //Controller
        BoardController boardController = new BoardController(paintableFieldTiles, paintableBases, game);
        GameController gameController = new GameController(game);
        List<JButton> buttons = gameController.getListOfButtons();


        boardPanel = new BoardPanel(paintableFieldTiles, paintableBases, paintableGoalTiles);
        drawPanel = new DrawPanel(boardPanel, buttons);

        new GameWindow("TurboFia", drawPanel, boardPanel, game);
    }
}