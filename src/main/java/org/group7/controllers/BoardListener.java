package org.group7.controllers;

import org.group7.model.Game;
import org.group7.model.Tile;
import org.group7.view.PaintableTile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardListener implements ActionListener {
    private Game game;

    public BoardListener(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintableTile paintableTile = (PaintableTile) e.getSource();
        Tile tile = paintableTile.getTile();
        //game.movePiece(tile);
    }
}
