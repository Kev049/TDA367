package org.group7;

import org.group7.controller.GameController;
import org.group7.controller.WindowController;
import org.group7.controller.observe.StringObserver;
import org.group7.model.board.Base;
import org.group7.model.board.Board;
import org.group7.model.board.IInsertable;
import org.group7.model.board.Tile;
import org.group7.model.entities.piece.Piece;
import org.group7.model.game.Game;
import org.group7.view.MenuWindow;
import org.group7.view.PaintableEntityFactory;
import org.group7.view.PaintableTileFactory;
import org.group7.view.paintables.PaintableBase;
import org.group7.view.paintables.PaintablePiece;
import org.group7.view.paintables.PaintableTile;
import org.group7.view.panels.game.BoardPanel;
import org.group7.view.panels.game.DrawGamePanel;
import org.group7.view.panels.game.LeftPanel;
import org.group7.view.panels.game.RightPanel;
import org.group7.view.panels.menu.DrawMenuPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        final String GAME_NAME = "TurboFia";
        final int TOTAL_AMOUNT_FIELD_TILES = 40;
        final int TOTAL_AMOUNT_BASES = 4;
        final int TOTAL_AMOUNT_GOAL_TILES = 16;
        final int TOTAL_AMOUNT_PIECES = 16;

        Game game = new Game();
        Board board = game.getBoard();

        Tile[] fieldTiles = board.getFieldTiles();
        List<Base> bases = board.getBases();
        List<IInsertable> goalTiles = board.getGoalTiles();

        List<PaintableTile> paintableFieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
        List<PaintableTile> paintableGoalStretchTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);

        List<PaintableBase> paintableBases = new ArrayList<>(TOTAL_AMOUNT_BASES);
        List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);

        PaintableEntityFactory paintableEntityFactory = new PaintableEntityFactory();

        for (Tile fieldTile : fieldTiles) {
            PaintableTile paintableTile = PaintableTileFactory.createTile(fieldTile);
            paintableFieldTiles.add(paintableTile);
        }

        List<PaintablePiece> coloredPaintablePieces = new ArrayList<>();
        for (Base base : bases) {
            Piece[] pieces = base.getPieces();
            coloredPaintablePieces.clear();
            for (Piece piece : pieces) {
                PaintablePiece paintablePiece = paintableEntityFactory.makePieceImage(piece);
                coloredPaintablePieces.add(paintablePiece);
                paintablePieces.add(paintablePiece);
            }
            PaintableBase paintableBase = new PaintableBase(base, coloredPaintablePieces);
            paintableBases.add(paintableBase);
        }

        for (IInsertable goalTile : goalTiles) {
            PaintableTile paintableTile = PaintableTileFactory.createTile(goalTile);
            paintableGoalStretchTiles.add(paintableTile);
        }

        GameController gameController = new GameController(game, paintablePieces, paintableBases);
        JButton rollDiceButton = gameController.getRollDiceButton();

        BoardPanel boardPanel = new BoardPanel(paintableFieldTiles, paintableBases, paintableGoalStretchTiles, paintablePieces);
        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel(rollDiceButton);
        JButton newGameButton = leftPanel.getNewGameButton();

        game.addObserver(boardPanel);
        game.addObserver((StringObserver) rightPanel);

        DrawGamePanel drawGamePanel = new DrawGamePanel(boardPanel, leftPanel, rightPanel);
        DrawMenuPanel drawMenuPanel = new DrawMenuPanel();
        JButton fourPlayerMenuButton = drawMenuPanel.getFourPlayerMenuButton();

        MenuWindow menuWindow = new MenuWindow(GAME_NAME, drawMenuPanel);
        WindowController windowController = new WindowController(menuWindow, drawGamePanel, drawMenuPanel, fourPlayerMenuButton, newGameButton);
    }
}