package org.group7.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Background extends JComponent {

    private final Image image;

    public Background(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 20;
        int y = 20;
        g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
    }
}

