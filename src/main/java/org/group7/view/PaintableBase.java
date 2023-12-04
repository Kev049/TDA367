package org.group7.view;

import org.group7.model.Base;
import org.group7.model.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaintableBase extends JButton{
    private Base base;
    List<PaintablePiece> paintablePieces = new ArrayList<>();
    public PaintableBase(Base base){
        this.base = base;
        this.setPreferredSize(new Dimension(91, 91));
        this.setMaximumSize(new Dimension(91, 91));
        this.setLayout(new GridBagLayout());
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
                Box box = new Box(BoxLayout.PAGE_AXIS);
                box.setPreferredSize(new Dimension(91, 91));
                //Add to panel
                this.add(box, c);
            }
        }
    }

    private void drawPieces(){
        int i = 0;
        for(Component component : this.getComponents()){
            if (component instanceof Box){
                ((Box) component).add(paintablePieces.get(i));
                i++;
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
