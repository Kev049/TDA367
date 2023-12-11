package org.group7.view;

import org.group7.model.Piece;
import javax.swing.*;
import java.awt.*;

public class PaintablePiece extends JButton{
    private Image image;
    private ImageIcon icon;
    private Piece piece;

    public PaintablePiece(Image image, Piece piece) {
        this.image = image;
        this.piece = piece;
        this.icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
    public ImageIcon getIcon(){
        return this.icon;
    }
    public Piece getPiece(){
        return piece;
    }
}
