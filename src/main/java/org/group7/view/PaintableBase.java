package org.group7.view;

import org.group7.model.Base;
import org.group7.model.Piece;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaintableBase extends JButton{
    private Base base;
    List<PaintablePiece> paintablePieces = new ArrayList<>();
    public PaintableBase(Base base){
        this.base = base;
        this.setLayout(new GridBagLayout());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        createPaintablePieces();
        drawBoxes();
        drawPieces();
    }

    private void drawBoxes(){
        GridBagConstraints c = new GridBagConstraints();
        for(int y = 0; y < 2; y++) {
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for (int x = 0; x < 2; x++) {
                c.gridx = x;
                //This will create a 11x11 grid of boxes of equal size.
                JPanel box = new JPanel();
                box.setLayout(new GridBagLayout());
                box.setPreferredSize(new Dimension(74, 74));
                box.setOpaque(false);

                //Add to panel
                this.add(box, c);
            }
        }
    }

    private void drawPieces(){
        int i = 0;
        for(Component component : this.getComponents()){
            if (component instanceof JPanel){
                PaintablePiece paintablePiece = paintablePieces.get(i++);
                ((JPanel) component).add(paintablePiece);
            }
        }
    }

    private void createPaintablePieces(){
        for(Piece piece : base.getPieces()){
            PaintablePiece paintablePiece = PaintableEntityFactory.makePieceImage(piece);
            paintablePieces.add(paintablePiece);
        }
    }

    public Base getBase(){
        return this.base;
    }
}
