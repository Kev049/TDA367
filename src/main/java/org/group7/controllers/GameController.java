package org.group7.controllers;

import org.group7.model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameController{
    private final JButton rollDiceButton = new JButton();
    private final JButton newGameButton = new JButton("New game");
    private final List<JButton> buttons = new ArrayList<>();
    private final RollDiceListener rollDiceListener;

    public GameController(Game game){
        this.rollDiceListener = new RollDiceListener(rollDiceButton, game);
        addButtonsToListOfButtons();
        addListeners();
    }

    public void addListeners(){
        Timer timer = new Timer(80, rollDiceListener);
        rollDiceButton.addActionListener(e -> rollDice(timer));
    }

    private void rollDice(Timer timer){
        rollDiceButton.setEnabled(false);
        timer.start();                      //TODO tycker inte det är jättetydligt vad detta gör
    }

    public void addButtonsToListOfButtons(){
        buttons.add(this.rollDiceButton);
        buttons.add(this.newGameButton);
    }

    public List<JButton> getListOfButtons(){
        return this.buttons;
    }
}
