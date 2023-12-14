package org.group7.controllers;

import org.group7.model.Game;
import org.group7.view.PaintableBase;
import org.group7.view.PaintablePiece;

import java.util.List;

public class BoardController { //TODO namnet på denna klassen borde ifrågasättas då den aldrig interagerar med Board direkt
    private final List<PaintablePiece> paintablePieces;
    private final List<PaintableBase> paintableBases;
    private final Game game;
    private final PaintableBaseListener paintableBaseListener;
    private final PaintablePieceListener paintablePieceListener;

    public BoardController(List<PaintablePiece> paintablePieces, List<PaintableBase> paintableBases,
                           Game game) {
        this.paintablePieces = paintablePieces;
        this.paintableBases = paintableBases;
        this.game = game;
        this.paintableBaseListener = new PaintableBaseListener(game);
        this.paintablePieceListener = new PaintablePieceListener(game);
        addListeners();
    }

    private void addListeners() {
        for (PaintablePiece paintablePiece : paintablePieces) {
            paintablePiece.addActionListener(paintablePieceListener);
        }
        for (PaintableBase paintableBase : paintableBases) {
            paintableBase.addActionListener(paintableBaseListener);
        }
    }
}
