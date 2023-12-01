package org.group7.view;

import org.group7.model.Piece;
import javax.swing.*;
import java.awt.*;

public class PaintablePiece extends JLabel{
    private Image image;
    private ImageIcon icon;
    private Piece piece;

    public PaintablePiece(Image image, Piece piece) {
        this.image = image;
        this.piece = piece;
        this.icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
    }
}
