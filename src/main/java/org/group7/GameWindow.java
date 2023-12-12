package org.group7;

import org.group7.model.Game;
import org.group7.view.BoardPanel;
import org.group7.view.DrawPanels;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;
    private Game game;  //TODO: Should probably remove this, unless it will serve a purpose in the future
    private DrawPanels drawPanel;
    private BoardPanel boardPanel; //TODO:Remove this when pieces are fixed, gameWindow should only have DrawPanel

    public GameWindow(String name, DrawPanels drawPanel, BoardPanel boardPanel, Game game){
        this.game = game;
        this.drawPanel = drawPanel;
        this.boardPanel = boardPanel;
        componentSetup(name);
    }

    private void componentSetup(String title){
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.add(drawPanel);
        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
