package org.group7;

import org.group7.view.DrawGamePanel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int X = 1920;
    private static final int Y = 1080;
    private static final int two = 2;
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
        setLocation(dim.width / two - this.getSize().width / two, dim.height / two - this.getSize().height / two);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
