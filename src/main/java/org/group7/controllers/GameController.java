package org.group7.controllers;

import org.group7.model.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameController{
    private JButton rollDiceButton = new JButton();
    private JButton newGameButton = new JButton("New game");
    private List<JButton> buttons = new ArrayList<>();
    private RollDiceListener rollDiceListener;
    private Game game;

    public GameController(Game game){
        this.game = game;
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
        timer.start();
    }

    public void addButtonsToListOfButtons(){
        buttons.add(this.rollDiceButton);
        buttons.add(this.newGameButton);
    }

    public List<JButton> getListOfButtons(){
        return this.buttons;
    }
}
