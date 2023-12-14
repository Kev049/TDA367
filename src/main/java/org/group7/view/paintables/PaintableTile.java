package org.group7.view.paintables;

import javax.swing.*;

import org.group7.model.board.Tile;

import java.awt.Dimension;
import java.awt.GridBagLayout;


public class PaintableTile extends JButton {

    private Tile tile;

    public PaintableTile(Tile tile) {
        this.tile = tile;
        this.setPreferredSize(new Dimension(91, 91));
        this.setMaximumSize(new Dimension(91, 91));
        this.setLayout(new GridBagLayout());
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    public Tile getTile() {
        return this.tile;
    }

}
