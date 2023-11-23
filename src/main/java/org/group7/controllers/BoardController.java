package org.group7.controllers;

import org.group7.model.Tile;
import org.group7.model.Board;
import org.group7.view.PaintableBoard;
import org.group7.view.PaintableTile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BoardController implements ActionListener {

    private Board board;

    public BoardController(Board board) {
        this.board = board;
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
        Tile t = (Tile)e.getSource();
        if (!t.isEmpty()) {             //Temorärt
                                // TODO Flytta skitpjäsen
        }
    }
}
