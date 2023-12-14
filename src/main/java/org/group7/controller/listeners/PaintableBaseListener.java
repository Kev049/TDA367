package org.group7.controller.listeners;

import org.group7.model.board.Base;
import org.group7.model.game.Game;
import org.group7.view.paintable.PaintableBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintableBaseListener implements ActionListener{
    private Game game;
    public PaintableBaseListener(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintableBase paintableBase = (PaintableBase) e.getSource();
        Base base = paintableBase.getBase();
        game.moveBasePiece(base.getColor());
    }
}