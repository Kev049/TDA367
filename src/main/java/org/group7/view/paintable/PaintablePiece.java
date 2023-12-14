package org.group7.view.paintable;

import org.group7.model.board.entities.piece.Piece;

import javax.swing.*;
import java.awt.*;

public class PaintablePiece extends JButton {
    private final ImageIcon icon;
    private final Piece piece;

    public PaintablePiece(Image image, Piece piece) {
        this.piece = piece;
        this.icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public Piece getPiece() {
        return piece;
    }
}
