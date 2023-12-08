package org.group7.controllers;

import org.group7.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class RollDiceButton extends JButton {

    private int diceRoll;
    private Game game;
    
    public RollDiceButton(Game game){
        this.game = game;

        JButton rollDiceButton = new JButton();
        rollDiceButton.setText("Roll!");
        rollDiceButton.setFont(new Font("Arial", Font.PLAIN, 40));
        rollDiceButton.setBounds(1600, 800, 200, 100);

        JTextField diceOutput = new JTextField("");
        diceOutput.setEditable(false);
        diceOutput.setText("");
        diceOutput.setFont(new Font("Arial", Font.PLAIN, 30));
        diceOutput.setBounds(1600, 700, 200, 80);

        this.add(rollDiceButton);
        this.add(diceOutput);

        //Flytta till en controller klass
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceRoll = game.roll();
                diceOutput.setText("You rolled " + diceRoll + "!");
            }
        });
    }
    private void initDiceRollComponents() {
        JButton rollDiceButton = new JButton();
        rollDiceButton.setText("Roll!");
        rollDiceButton.setFont(new Font("Arial", Font.PLAIN, 40));
        rollDiceButton.setBounds(1600, 800, 200, 100);

        JTextField diceOutput = new JTextField("");
        diceOutput.setEditable(false);
        diceOutput.setText("");
        diceOutput.setFont(new Font("Arial", Font.PLAIN, 30));
        diceOutput.setBounds(1600, 700, 200, 80);

        this.add(rollDiceButton);
        this.add(diceOutput);

        //Flytta till en controller klass
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceRoll = game.roll();
                diceOutput.setText("You rolled " + diceRoll + "!");
            }
        });
    }
}


