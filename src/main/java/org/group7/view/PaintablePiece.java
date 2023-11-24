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
        initPaintablePiece(); //Behövs bättre namngivning än init, men jag är för trög för att komma på bättre
    }
    private void initPaintablePiece(){
        this.setIcon(this.icon);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
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
