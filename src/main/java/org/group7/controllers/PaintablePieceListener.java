package org.group7.controllers;

import org.group7.model.Game;
import org.group7.model.Piece;
import org.group7.view.PaintablePiece;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintablePieceListener implements ActionListener{
    private Game game;

    public PaintablePieceListener(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintablePiece paintablePiece = (PaintablePiece) e.getSource();
        Piece piece = paintablePiece.getPiece();
        game.move(piece);
    }
}
