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
        ArrayList<Tile> tiles = board.getAllTiles();
        ArrayList<Piece> pieces = board.getAllPieces();

        for (int i = 0; i < TOTAL_AMOUNT_TILES; i++){
            int baseIndex = 40;
            if(i < 4) tile = null;

            //RED BASE
            else if(i > 11 && i < 14){
                tile = tiles.get(baseIndex);
                baseIndex ++;
            }

            else if(i > 22 && i < 25) tile = tiles.get(baseIndex);

            //BLUE BASE
            else if(i > 18 && i < 21) tile = tiles.get(baseIndex);
            else if(i > 29 && i < 32) tile = tiles.get(baseIndex);

            //YELLOW BASE
            else if(i > 88 && i < 91) tile = tiles.get(baseIndex);
            else if(i > 99 && i < 102) tile = tiles.get(baseIndex);

            //GREEN BASE
            else if(i > 95 && i < 98) tile = tiles.get(baseIndex);
            else if(i > 106 && i < 109) tile = tiles.get(baseIndex);


            //Common path

            if(i > 43 && i < 49) tiles.get(p);
            else if(i == 37) tiles.get()


            else if (i > 3 && i < 7) tiles.get(i);
            else if (i == 15 || i = 17) tiles.get(i);
            else if (i == 26 || i = 28) tiles.get(i);
            else if (i == 37 || i = 39) tiles.get(i);
            else if (i > 44 && i < 55 && i!= 49) tiles.get(i);
            else if (i > 49 && i < ) tiles.get(i);
            else if (i == 15 || i = 17) tiles.get(i);
            else if (i == 26 || i = 28) tiles.get(i);
            else if (i == 26 || i = 28) tiles.get(i);
            else if (i > 3 && i < 7) tiles.get(i);

            else if (i>116) tile = null;
            PaintableTile paintableTile = TileFactory.createTile(tile);
            paintableTiles.add(paintableTile);

        }

        for (int i = 0; i < TOTAL_AMOUNT_PIECES; i++){
            PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(pieces.get(i));
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