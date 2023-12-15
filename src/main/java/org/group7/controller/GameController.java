package org.group7.controller;

import org.group7.controller.listeners.PaintableBaseListener;
import org.group7.controller.listeners.PaintablePieceListener;
import org.group7.controller.listeners.RollDiceListener;
import org.group7.model.game.Game;
import org.group7.view.paintables.PaintableBase;
import org.group7.view.paintables.PaintablePiece;

import javax.swing.*;
import java.util.List;

/*
 * This class is responsible for controlling the flow of the game
 * and takes care of functionality like adding listeners and rolling the dice.
 */
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
        //Add a timer that has rollDiceListener as action listener
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
        //Disable button and execute code in action performed in RollDiceListener
        rollDiceButton.setEnabled(false);
        timer.start();
    }

    public JButton getRollDiceButton() {
        return this.rollDiceButton;
    }
}
