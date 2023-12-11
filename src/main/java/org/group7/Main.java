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
    private static DrawPanels drawPanel;
    private static List<PaintableTile> paintableFieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
    private static List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);
    private static List<PaintableTile> paintableGoalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);
    private static List<PaintableBase> paintableBases = new ArrayList<>(TOTAL_AMOUNT_BASES);
    private static Tile fieldTiles[];
    private static List<Base> bases = new ArrayList<>(TOTAL_AMOUNT_BASES);
    private static List<Tile> goalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);

    public static void main(String[] args) {
        //Model instances
        Board board = new Board();
        Game game = new Game(board);

        //View instances
        fieldTiles = board.getFieldTiles();
        bases = board.getBases();
        goalTiles = board.getGoalTiles();

        for (Tile fieldTile : fieldTiles) {
            PaintableTile paintableTile = PaintableTileFactory.createTile(fieldTile);
            paintableFieldTiles.add(paintableTile);
        }

        List<PaintablePiece> coloredPaintablePieces = new ArrayList<>();
        for(Base base : bases){
            Piece[] pieces = base.getPieces();
            coloredPaintablePieces.clear();
            for(int i = 0; i < pieces.length; i++){
                PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(pieces[i]);
                coloredPaintablePieces.add(paintablePiece);
                paintablePieces.add(paintablePiece);
            }
            PaintableBase paintableBase = new PaintableBase(base, coloredPaintablePieces);
            paintableBases.add(paintableBase);
        }


        for (Tile goalTile : goalTiles) {
            PaintableTile paintableTile = PaintableTileFactory.createTile(goalTile);
            paintableGoalTiles.add(paintableTile);
        }

        GameController gameController = new GameController(game);
        List<JButton> buttons = gameController.getListOfButtons();

        boardPanel = new BoardPanel(paintableFieldTiles, paintableBases, paintableGoalTiles, paintablePieces);
        drawPanel = new DrawPanels(boardPanel, buttons, game);

        //Controller
        BoardController boardController = new BoardController(paintablePieces, paintableBases, game, board, boardPanel);

        new GameWindow("TurboFia", drawPanel, boardPanel, game);
    }
}