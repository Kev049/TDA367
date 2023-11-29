package org.group7.controllers;

import org.group7.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Borde kanske nämna om till BoardListener
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
        //Tile t = (Tile)e.getSource();
        System.out.println(e.getSource());
        /*
        if (!t.isEmpty()) {             //Temorärt
            if (game.validateMove(t))    //TODO måste veta color för att göra flytten
            game.movePiece(t.getIndex());
        }

         */
    }
}
