package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.model.Board;
import org.group7.model.Game;
import org.group7.model.Piece;
import org.group7.model.Tile;
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
    private static final int TOTAL_AMOUNT_BASE_TILES = 16;
    private static final int TOTAL_AMOUNT_GOAL_TILES = 16;
    private static final int TOTAL_AMOUNT_PIECES = 16;
    private static BoardPanel boardPanel;
    private static DrawPanel drawPanel;
    private static List<PaintableTile> paintableFieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
    private static List<PaintableTile> paintableBaseTiles = new ArrayList<>(TOTAL_AMOUNT_BASE_TILES);
    private static List<PaintableTile> paintableGoalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);
    private static List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);

    public static void main(String[] args) {
        //Model instances
        Board board = new Board();
        Game game = new Game();

        //View instances
        ArrayList<Tile> tiles = board.getAllTiles();
        ArrayList<Piece> pieces = board.getAllPieces();

        //Det här kan def. förbättras, TODO: Kalla på separata metoder för varje lista istället för en gemensam

        for (int i = 0; i < TOTAL_AMOUNT_TILES; i++){
            if(i < TOTAL_AMOUNT_FIELD_TILES){
                PaintableTile paintableTile = TileFactory.createTile(tiles.get(i));
                paintableFieldTiles.add(paintableTile);
            }
            else if(i < TOTAL_AMOUNT_FIELD_TILES + TOTAL_AMOUNT_BASE_TILES){
                PaintableTile paintableTile = TileFactory.createTile(tiles.get(i));
                paintableBaseTiles.add(paintableTile);
            }
            else{
                PaintableTile paintableTile = TileFactory.createTile(tiles.get(i));
                paintableGoalTiles.add(paintableTile);
            }
        }

        for (int i = 0; i < TOTAL_AMOUNT_PIECES; i++){
            PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(pieces.get(i));
            paintablePieces.add(paintablePiece);
        }

        //Controller
        BoardController boardController = new BoardController(paintableFieldTiles, game);
        GameController gameController = new GameController(game);
        List< JButton> buttons = gameController.getListOfButtons();


        boardPanel = new BoardPanel(paintableFieldTiles, paintableBaseTiles, paintableGoalTiles, paintablePieces);
        drawPanel = new DrawPanel(boardPanel, buttons);

        new GameWindow("TurboFia", drawPanel, boardPanel, game);
    }
}