package org.group7.controller.listeners;

import org.group7.model.game.Game;
import org.group7.model.board.entities.piece.Piece;
import org.group7.view.paintables.PaintablePiece;

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
