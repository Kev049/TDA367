package org.group7.controllers;

import org.group7.model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController{
    private JButton rollDiceButton = new JButton("Roll dice!");
    private Game game;

    public GameController(Game game){
        this.game = game;
        addListeners();
    }

    public void addListeners(){
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = game.rollDice();
            }
        });
    }

    public JButton getRollDiceButton(){
        return this.rollDiceButton;
    }
}
