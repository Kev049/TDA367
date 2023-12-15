package org.group7.view.paintables;

import org.group7.model.board.entities.powerups.PowerUp;

import javax.swing.*;
import java.awt.*;

public class PaintablePowerUp extends JLabel {
    private final PowerUp powerup;

    public PaintablePowerUp(Image image, PowerUp powerUp) {
        this.powerup = powerUp;
        this.setIcon(new ImageIcon(image));
        this.setOpaque(false);
    }

    public PowerUp getPowerup() {
        return powerup;
    }
}
