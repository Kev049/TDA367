package org.group7.view;

import org.group7.model.Base;
import org.group7.model.Piece;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaintableBase extends JButton{
    private Base base;
    List<PaintablePiece> paintablePieces = new ArrayList<>();
    HashMap<Piece, PaintablePiece> paintablePiecesHash = new HashMap<>();
    public PaintableBase(Base base, List<PaintablePiece> paintablePieces){
        this.base = base;
        this.paintablePieces = paintablePieces;
        this.setLayout(new GridBagLayout());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        initPaintablePiecesHashMap();
        drawBoxes();
        drawPieces();
    }

    private void initPaintablePiecesHashMap(){
        for(PaintablePiece paintablePiece : paintablePieces){
            Piece piece = paintablePiece.getPiece();
            paintablePiecesHash.put(piece, paintablePiece);
        }
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
                JLabel pieceLabel = new JLabel();
                pieceLabel.setIcon(paintablePiece.getIcon());
                ((JPanel) component).add(pieceLabel);
            }
        }
    }

    public void redrawPieces(){
        for(Component component : this.getComponents()){
            if (component instanceof JPanel){
                ((JPanel) component).removeAll();
            }
        }
        int i = 0;
        for(Piece piece : base.getPieces()){
            if(piece != null){
                JPanel component = (JPanel) this.getComponent(i++);
                PaintablePiece paintablePiece = paintablePiecesHash.get(piece);
                JLabel pieceLabel = new JLabel();
                pieceLabel.setIcon(paintablePiece.getIcon());
                ((JPanel) component).add(pieceLabel);
            }
        }
    }

    public Base getBase(){
        return this.base;
    }
}
