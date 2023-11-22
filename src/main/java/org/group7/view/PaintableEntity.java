package org.group7.view;

import org.group7.model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintableEntity extends JComponent{
    private Entity entity;
    private BufferedImage image;

    public PaintableEntity(BufferedImage image, Entity entity) {
        this.image = image;
        this.entity = entity;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
    }

}
