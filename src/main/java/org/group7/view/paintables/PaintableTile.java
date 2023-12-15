package org.group7.view.paintables;

import javax.swing.*;

import org.group7.model.board.IInsertable;

import java.awt.Dimension;
import java.awt.GridBagLayout;


public class PaintableTile extends JButton {

    private IInsertable tile;

    public PaintableTile(IInsertable tile) {
        this.tile = tile;
        this.setPreferredSize(new Dimension(91, 91));
        this.setMaximumSize(new Dimension(91, 91));
        this.setLayout(new GridBagLayout());
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    public IInsertable getTile() {
        return this.tile;
    }

}
