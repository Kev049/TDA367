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
    private Game game;  //TODO: Should probably remove this, unless it will serve a purpose in the future
    private DrawPanel drawPanel;
    private BoardPanel boardPanel; //TODO:Remove this when pieces are fixed, gameWindow should only have DrawPanel

    public GameWindow(String name, DrawPanel drawPanel, BoardPanel boardPanel, Game game){
        this.game = game;
        this.drawPanel = drawPanel;
        this.boardPanel = boardPanel;
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
        Icon icon = new ImageIcon("src/main/resources/green_player_circle.png");
        JButton piece = new JButton(icon);
        piece.setPreferredSize(new Dimension());
        piece.setBorderPainted(false);
        piece.setContentAreaFilled(false);
        piece.setFocusPainted(false);
        piece.setOpaque(false);
        /*
        List<Integer> index = boardPanel.getGamePathTileIndexes();
        HashMap<Integer, PaintableTile> indexTileHashMap = boardPanel.getindexTileHashMap();
        PaintableTile currentTile = indexTileHashMap.get(index.get(0));
        currentTile.add(piece);
        piece.addActionListener(new ActionListener() {
            int currentPos = 0;
            List<Integer> index = boardPanel.getGamePathTileIndexes();
            HashMap<Integer, PaintableTile> indexTileHashMap = boardPanel.getindexTileHashMap();
            PaintableTile currentTile = indexTileHashMap.get(index.get(0));
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = game.getLastDiceRollResult();
                //Add result from dice roll to current position and move around without index out of length of array
                currentPos = (currentPos + diceRoll) % 40;

                //Remove piece from current tile it is on
                currentTile.remove(piece);
                currentTile.repaint();
                currentTile.revalidate();

                //Get tile from the new Point given and add the piece to the tile it has moved to
                currentTile = indexTileHashMap.get(index.get(currentPos));
                currentTile.add(piece);
            }
        });

         */
    }
}
