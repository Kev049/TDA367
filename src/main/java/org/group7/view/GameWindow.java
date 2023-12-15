package org.group7.view;

import org.group7.view.panels.game.DrawGamePanel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int X = 1920;
    private static final int Y = 1080;
    private static final int HALF = 2;
    private final DrawGamePanel drawPanel;

    public GameWindow(String name, DrawGamePanel drawPanel) {
        this.drawPanel = drawPanel;
        componentSetup(name);
    }

    private void componentSetup(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.add(drawPanel);
        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width / HALF - this.getSize().width / HALF, dim.height / HALF - this.getSize().height / HALF);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
