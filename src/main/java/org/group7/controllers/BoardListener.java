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
        /*List<PaintableTile> tiles = paintableBoard.getPaintableTiles();
        for (PaintableTile tile : tiles) {
            tile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //board move here
                }
            });
        }*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintableTile paintableTile = (PaintableTile) e.getSource();
        Tile tile = paintableTile.getTile();
        System.out.println(e.getSource());
        game.movePiece(tile);
        /*  TODO: Gör klart modellen så vi kan implementera det här
        if (!tile.isEmpty()) {             //Temorärt
            if (game.validateMove(tile))    //TODO måste veta color för att göra flytten
            game.movePiece(tile.getIndex());
        }
         */
    }
}
