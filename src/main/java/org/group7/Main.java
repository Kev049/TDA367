package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.model.Board;
import org.group7.model.Game;
import org.group7.model.Tile;
import org.group7.view.*;
//import controller.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    private static final int TOTAL_AMOUNT_TILES = 121;
    private static final int TOTAL_AMOUNT_PIECES = 16;
    private static BoardPanel boardPanel;
    private static DrawPanel drawPanel;
    private static List<PaintableTile> paintableTiles = new ArrayList<>(TOTAL_AMOUNT_TILES);
    private static List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);

    public static void main(String[] args) {
        //Model instances
        Board board = new Board();
        Game game = new Game();

        //View instances
        Tile tile = null;
        //Tile[] tiles = board.getAllTiles();
        Pieces[] pieces = board.getAllPieces();
        for (int i = 0; i < TOTAL_AMOUNT_TILES; i++) {
            PaintableTile paintableTile = TileFactory.createTile(tiles[i]);
            paintableTiles.add(paintableTile);
        }
        for (int i = 0; i < TOTAL_AMOUNT_PIECES; i++){
            PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(pieces[i]);
            paintablePieces.add(paintablePiece);
        }

        //Controller
        BoardController boardController = new BoardController(paintableTiles, game);
        GameController gameController = new GameController(game);
        List< JButton> buttons = gameController.getListOfButtons();


        boardPanel = new BoardPanel(paintableTiles, paintablePieces);
        drawPanel = new DrawPanel(boardPanel, buttons);

        new GameWindow("TurboFia", drawPanel, boardPanel, game);
    }
}