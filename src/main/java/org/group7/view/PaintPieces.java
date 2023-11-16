package org.group7.view;

import org.group7.model.Piece;

import javax.swing.*;
import java.awt.*;

public class PaintPieces extends JComponent {
    private Image image;
    private Piece piece;

    public PaintPieces(Image image, Piece piece) {
        this.image = image;
        this.piece = piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
    }
}
