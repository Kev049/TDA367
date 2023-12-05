package org.group7.view;

import javax.swing.*;

import org.group7.controllers.BoardListener;
import org.group7.model.Tile;

import java.awt.*;

public class PaintableTile extends JButton {

    private Tile tile = null;

    public PaintableTile(Tile tile, PaintablePiece paintablePiece){
        this.tile = tile;
        this.setPreferredSize(new Dimension(91, 91));
        this.setMaximumSize(new Dimension(91, 91));
        this.setLayout(new GridLayout());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        if(paintablePiece != null) this.add(paintablePiece);
    }

    public Tile getTile(){
        return this.tile;
    }
}
