package org.group7.controller;

import org.group7.controller.listeners.PaintableBaseListener;
import org.group7.controller.listeners.PaintablePieceListener;
import org.group7.controller.listeners.RollDiceListener;
import org.group7.model.game.Game;
import org.group7.view.paintables.PaintableBase;
import org.group7.view.paintables.PaintablePiece;

import javax.swing.*;
import java.util.List;

public class GameController {
    private final JButton rollDiceButton = new JButton();
    private final RollDiceListener rollDiceListener;
    private final PaintableBaseListener paintableBaseListener;
    private final PaintablePieceListener paintablePieceListener;

    public GameController(Game game, List<PaintablePiece> paintablePieces, List<PaintableBase> paintableBases) {
        this.rollDiceListener = new RollDiceListener(rollDiceButton, game);
        this.paintableBaseListener = new PaintableBaseListener(game);
        this.paintablePieceListener = new PaintablePieceListener(game);
        addListeners(paintablePieces, paintableBases);
    }

    public void addListeners(List<PaintablePiece> paintablePieces, List<PaintableBase> paintableBases) {
        Timer timer = new Timer(80, rollDiceListener);
        rollDiceButton.addActionListener(e -> rollDice(timer));

        for (PaintablePiece paintablePiece : paintablePieces) {
            paintablePiece.addActionListener(paintablePieceListener);
        }
        for (PaintableBase paintableBase : paintableBases) {
            paintableBase.addActionListener(paintableBaseListener);
        }
    }

    private void rollDice(Timer timer) {
        rollDiceButton.setEnabled(false);
        timer.start();                      //TODO tycker inte det är jättetydligt vad detta gör
    }

    public JButton getRollDiceButton() {
        return this.rollDiceButton;
    }
}
