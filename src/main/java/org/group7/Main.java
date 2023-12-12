package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.controllers.WindowController;
import org.group7.model.*;
import org.group7.view.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //TODO varför har vi attribut i main när det endast kopplar ihop programmet?
    private static final int TOTAL_AMOUNT_FIELD_TILES = 40;
    private static final int TOTAL_AMOUNT_BASES = 4;
    private static final int TOTAL_AMOUNT_GOAL_TILES = 16;
    private static final int TOTAL_AMOUNT_PIECES = 16;
    private static BoardPanel boardPanel;
    private static DrawGamePanel drawPanel;
    private static List<PaintableTile> paintableFieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
    private static List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);
    private static List<PaintableTile> paintableGoalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);
    private static List<PaintableBase> paintableBases = new ArrayList<>(TOTAL_AMOUNT_BASES);
    private static Tile[] fieldTiles;
    private static List<Base> bases = new ArrayList<>(TOTAL_AMOUNT_BASES);
    private static List<Tile> goalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);

    public static void main(String[] args) {    //TODO försöka att städa denna klass
        Board board = new Board(); //TODO: Flytta tillbaka till game
        Game game = new Game(board);

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
            for (Piece piece : pieces) {
                PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(piece);
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
        //TODO: refactor please
        GameController gameController = new GameController(game);
        List<JButton> buttons = gameController.getListOfButtons();

        boardPanel = new BoardPanel(paintableFieldTiles, paintableBases, paintableGoalTiles, paintablePieces);
        //Move right and left panel to main, pass down to DrawPanels
        drawPanel = new DrawGamePanel(boardPanel, buttons, game);
        DrawMenuPanel drawMenuPanel = new DrawMenuPanel();

        JButton fourPlayerMenuButton = drawMenuPanel.getFourPlayerMenuButton();
        BoardController boardController = new BoardController(paintablePieces, paintableBases, game, boardPanel); //TODO borde kanske ändra detta, används men "ändå inte"

        MenuWindow menuWindow = new MenuWindow("TurboFia", drawMenuPanel);
        WindowController windowController = new WindowController(menuWindow, drawPanel, fourPlayerMenuButton);

    }
}