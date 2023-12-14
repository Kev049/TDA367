package org.group7;

import org.group7.view.DrawMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame {
    private static final int X = 1920;
    private static final int Y = 1080;
    private DrawMenuPanel drawMenuPanel;

    public MenuWindow(String name, DrawMenuPanel drawMenuPanel) {
        this.drawMenuPanel = drawMenuPanel;
        componentSetup(name);
    }

    private void componentSetup(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.add(drawMenuPanel);
        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
