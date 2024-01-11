package org.group7.view.paintables;

import javax.swing.*;

import org.group7.model.board.IInsertable;

import java.awt.Dimension;
import java.awt.GridBagLayout;


public class PaintableTile extends JButton {

    private IInsertable tile;
    private final int TILE_SIZE = 91;

    public PaintableTile(IInsertable tile) {
        this.tile = tile;
        this.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        this.setMaximumSize(new Dimension(TILE_SIZE, TILE_SIZE));
        this.setLayout(new GridBagLayout());
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    public IInsertable getTile() {
        return this.tile;
    }

}
