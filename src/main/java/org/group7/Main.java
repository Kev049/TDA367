package org.group7;

import org.group7.controllers.BoardController;
import org.group7.controllers.GameController;
import org.group7.controllers.WindowController;
import org.group7.model.*;
import org.group7.view.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //TODO varför har vi attribut i main när det endast kopplar ihop programmet?
    private static final int TOTAL_AMOUNT_FIELD_TILES = 40;
    private static final int TOTAL_AMOUNT_BASES = 4;
    private static final int TOTAL_AMOUNT_GOAL_TILES = 16;
    private static final int TOTAL_AMOUNT_PIECES = 16;

    public static void main(String[] args) {    //TODO försöka att städa denna klass
        Board board = new Board(); //TODO: Flytta tillbaka till game
        Game game = new Game(board);

        Tile[] fieldTiles = board.getFieldTiles();
        List<Base> bases = board.getBases();
        List<Tile> goalTiles = board.getGoalTiles();

        List<PaintableTile> paintableFieldTiles = new ArrayList<>(TOTAL_AMOUNT_FIELD_TILES);
        List<PaintableTile> paintableGoalTiles = new ArrayList<>(TOTAL_AMOUNT_GOAL_TILES);

        List<PaintableBase> paintableBases = new ArrayList<>(TOTAL_AMOUNT_BASES);
        List<PaintablePiece> paintablePieces = new ArrayList<>(TOTAL_AMOUNT_PIECES);

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
        JButton rollDiceButton = gameController.getRollDiceButton();

        BoardPanel boardPanel = new BoardPanel(paintableFieldTiles, paintableBases, paintableGoalTiles, paintablePieces);
        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel(rollDiceButton);
        JButton newGameButton = leftPanel.getNewGameButton();

        DrawGamePanel drawGamePanel = new DrawGamePanel(boardPanel, game, leftPanel, rightPanel);
        DrawMenuPanel drawMenuPanel = new DrawMenuPanel();
        JButton fourPlayerMenuButton = drawMenuPanel.getFourPlayerMenuButton();

        BoardController boardController = new BoardController(paintablePieces, paintableBases, game, boardPanel); //TODO borde kanske ändra detta, används men "ändå inte"

        MenuWindow menuWindow = new MenuWindow("TurboFia", drawMenuPanel);
        WindowController windowController = new WindowController(menuWindow, drawGamePanel, drawMenuPanel, fourPlayerMenuButton, newGameButton);
        playSound();
    }

    public static synchronized void playSound() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    (new File("src/main/resources/gus.wav")));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}