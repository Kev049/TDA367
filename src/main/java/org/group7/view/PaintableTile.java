package org.group7.view;

import javax.swing.*;

import org.group7.controllers.BoardListener;
import org.group7.model.Tile;

import java.awt.*;

public class PaintableTile extends JButton {

    private Tile tile = null;

    public PaintableTile(Tile tile, BoardListener boardListener){
        this.tile = tile;
        this.setPreferredSize(new Dimension(91, 91));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addActionListener(boardListener);
    }

    public Tile getTile(){
        return this.tile;
    }
}
