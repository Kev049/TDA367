package org.group7.view;

import org.group7.model.PowerUp;

import javax.swing.*;
import java.awt.*;

public class PaintablePowerUp extends JLabel {
    private Image image;
    private ImageIcon icon;
    private PowerUp powerup;

    public PaintablePowerUp(Image image, PowerUp powerUp){
        this.image = image;
        this.powerup = powerUp;
        this.icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setOpaque(false);
    }

    public PowerUp getPowerup() {
        return powerup;
    }
}
