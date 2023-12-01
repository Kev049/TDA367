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
}
