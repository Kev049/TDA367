package org.group7.controllers;

import org.group7.model.Game;

import javax.swing.*;

public class GameController {
    private final JButton rollDiceButton = new JButton();
    private final RollDiceListener rollDiceListener;

    public GameController(Game game) {
        this.rollDiceListener = new RollDiceListener(rollDiceButton, game);
        addListeners();
    }

    public void addListeners() {
        Timer timer = new Timer(80, rollDiceListener);
        rollDiceButton.addActionListener(e -> rollDice(timer));
    }

    private void rollDice(Timer timer) {
        rollDiceButton.setEnabled(false);
        timer.start();                      //TODO tycker inte det är jättetydligt vad detta gör
    }

    public JButton getRollDiceButton() {
        return this.rollDiceButton;
    }
}
