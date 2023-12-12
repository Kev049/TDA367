package org.group7.view;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Graphics;

public class Background extends JPanel { //TODO antar att denna är WIP?

    private final Image image;

    public Background(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
    }
}

