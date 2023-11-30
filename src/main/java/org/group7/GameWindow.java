package org.group7;

import org.group7.controllers.BoardListener;
import org.group7.controllers.GameController;
import org.group7.model.Board;
import org.group7.model.Game;
import org.group7.model.Tile;
import org.group7.view.BoardPanel;
import org.group7.view.DrawPanel;
import org.group7.view.PaintableTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.List;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;

    int diceRoll;
    int currentPos = 0;
    private Game game;
    private DrawPanel drawPanel;
    private PaintableTile currentTile; //TODO: Remove this
    private BoardPanel boardPanel; //TODO:Remove this, gameWindow should only have DrawPanel
    private BoardListener boardListener;
    private GameController gameController;
    private Board board;
    private List<JButton> buttons = new ArrayList<>();
    private List<PaintableTile> paintableTiles = new ArrayList<>();


    public GameWindow(String name, Board board, GameController gameController){
        this.game = new Game();
        this.board = board;
        this.gameController = gameController;
        this.buttons = gameController.getListOfButtons();
        this.boardListener = new BoardListener(game);
        this.boardPanel = new BoardPanel(board, boardListener, paintableTiles);
        this.drawPanel = new DrawPanel(boardPanel, buttons);
        componentSetup(name);
    }

    private void componentSetup(String title){
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.add(drawPanel);
        initPieces();

        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initPieces(){
        List<Integer> index = boardPanel.getGamePathTileIndexes();
        HashMap<Integer, PaintableTile> indexTileHashMap = boardPanel.getindexTileHashMap();
        Icon icon = new ImageIcon("src/main/resources/red_player_circle.png");
        JLabel piece = new JLabel(icon);
        piece.setPreferredSize(new Dimension());
//      piece.setBorderPainted(false);
//        piece.setContentAreaFilled(false);
//        piece.setFocusPainted(false);
        piece.setOpaque(false);
        currentPos = 18;
        currentTile = indexTileHashMap.get(index.get(currentPos));
        currentTile.add(piece);
        //Controller
//        piece.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Add result from dice roll to current position and move around without index out of length of array
//                currentPos = (currentPos + diceRoll) % 40;
//
//                //Remove piece from current tile it is on
//                currentTile.remove(piece);
//                currentTile.repaint();
//                currentTile.revalidate();
//
//                //Get tile from the new Point given and add the piece to the tile it has moved to
//                currentTile = indexTileHashMap.get(index.get(currentPos));
//                currentTile.add(piece);
//            }
//        });
    }
}
