package org.group7.view.paintable;

import org.group7.model.board.entities.powerups.PowerUp;

import javax.swing.*;
import java.awt.*;

public class PaintablePowerUp extends JLabel {
    private final ImageIcon icon;         //TODO vill man kunna ändra/hämta? Annars behöver detta inte vara ett attribut
    private final PowerUp powerup;

    public PaintablePowerUp(Image image, PowerUp powerUp) {
        this.powerup = powerUp;
        this.icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setOpaque(false);
    }

    public PowerUp getPowerup() {
        return powerup;
    }
}
