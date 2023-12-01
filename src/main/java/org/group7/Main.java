package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.model.Board;
import org.group7.model.Game;
import org.group7.model.Tile;
import org.group7.view.DrawPanel;
import org.group7.view.BoardPanel;
import org.group7.view.PaintableTile;
import org.group7.view.TileFactory;
//import controller.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    private static final int TOTAL_AMOUNT_TILES = 121;
    private static BoardPanel boardPanel;
    private static DrawPanel drawPanel;
    private static List<PaintableTile> paintableTiles = new ArrayList<>(TOTAL_AMOUNT_TILES);

    public static void main(String[] args) {
        //Model instances
        Board board = new Board();
        Game game = new Game();

        //Controller
        BoardController boardController = new BoardController(paintableTiles, game);
        GameController gameController = new GameController(game);
        List< JButton> buttons = gameController.getListOfButtons();

        //View instances
        Tile tile = null;
        for (int i = 0; i < TOTAL_AMOUNT_TILES; i++) {
            PaintableTile paintableTile = TileFactory.createTile(tile);
            paintableTiles.add(paintableTile);
        }
        boardPanel = new BoardPanel(paintableTiles);
        drawPanel = new DrawPanel(boardPanel, buttons);

        new GameWindow("TurboFia", drawPanel, boardPanel);
    }
}