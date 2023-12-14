package org.group7.controllers;

import org.group7.model.*;
import org.group7.view.BoardPanel;
import org.group7.view.PaintableBase;
import org.group7.view.PaintablePiece;

import java.util.List;

public class BoardController { //TODO namnet på denna klassen borde ifrågasättas då den aldrig interagerar med Board direkt
    private final List<PaintablePiece> paintablePieces;
    private final List<PaintableBase> paintableBases;
    private final Game game;
    private final BoardPanel boardPanel;

    public BoardController(List<PaintablePiece> paintablePieces, List<PaintableBase> paintableBases,
                           Game game, BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.paintablePieces = paintablePieces;
        this.paintableBases = paintableBases;
        this.game = game;
        addListeners();
    }

    private void addListeners() {
        for (PaintablePiece paintablePiece : paintablePieces) {
            paintablePiece.addActionListener(e -> {
                Piece piece = paintablePiece.getPiece();
                game.move(piece);
                boardPanel.drawBoard();
            });
        }
        for (PaintableBase paintableBase : paintableBases) {
            paintableBase.addActionListener(e -> { // TODO säg åt game att byta spelare
                Base base = paintableBase.getBase();
                game.moveBasePiece(base.getColor());
                paintableBase.redrawPieces();
                boardPanel.drawBoard();
            });
        }
    }
}
