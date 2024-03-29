package org.group7.view.paintables;

import org.group7.model.board.Base;
import org.group7.model.entities.piece.Piece;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Component;

import java.util.HashMap;
import java.util.List;

public class PaintableBase extends JButton {
    private final Base base;
    private final List<PaintablePiece> paintablePieces;
    private final HashMap<Piece, PaintablePiece> paintablePiecesHash = new HashMap<>();

    public PaintableBase(Base base, List<PaintablePiece> paintablePieces) {
        this.base = base;
        this.paintablePieces = paintablePieces;
        this.setLayout(new GridBagLayout());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        initPaintablePiecesHashMap();
        drawBoxes();
        drawPieces();
    }

    private void initPaintablePiecesHashMap() {
        for (PaintablePiece paintablePiece : paintablePieces) {
            Piece piece = paintablePiece.getPiece();
            paintablePiecesHash.put(piece, paintablePiece);
        }
    }

    private void drawBoxes() {
        int boxSize = 74;
        GridBagConstraints c = new GridBagConstraints();
        for (int y = 0; y < 2; y++) {
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for (int x = 0; x < 2; x++) {
                c.gridx = x;
                //This will create a 11x11 grid of boxes of equal size.
                JPanel box = new JPanel();
                box.setLayout(new GridBagLayout());
                box.setPreferredSize(new Dimension(boxSize, boxSize));
                box.setOpaque(false);

                //Add to panel
                this.add(box, c);
            }
        }
    }

    private void drawPieces() {
        int i = 0;
        for (Piece piece : base.getPieces()) {
            if (piece != null) {
                JPanel component = (JPanel) this.getComponent(i++);
                PaintablePiece paintablePiece = paintablePiecesHash.get(piece);
                JLabel pieceLabel = new JLabel();
                pieceLabel.setIcon(paintablePiece.getIcon());
                component.add(pieceLabel);
            }
        }
    }

    public void redrawPieces(){
        for (Component component : this.getComponents()) {
            if (component instanceof JPanel jPanel) {
                jPanel.removeAll();
            }
        }
        drawPieces();
    }

    public Base getBase() {
        return this.base;
    }
}
