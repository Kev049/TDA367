package org.group7.controllers;

import org.group7.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController{
    private JButton rollDiceButton = new JButton();
    private JButton newGameButton = new JButton("New game");
    private List<JButton> buttons = new ArrayList<>();
    private List<Point> imageCoordinates = new ArrayList<>();
    private Game game;

    public GameController(Game game){
        this.game = game;
        initDiceImageCoordinates();
        addListeners();
        addButtonsToListOfButtons();
    }

    public void addListeners(){
        rollDiceButton.addActionListener(e -> rollDice());
    }

    private void initDiceImageCoordinates(){
        Collections.addAll(imageCoordinates, new Point(0, 0), new Point(205, 0),
                new Point(408, 0), new Point(0, 203), new Point(205, 203),
                new Point(408, 203));
    }

    private void rollDice(){
        int diceRollResult = game.rollDice() - 1;
        System.out.println(diceRollResult);
        BufferedImage image = new BufferedImage(205, 205, BufferedImage.TYPE_INT_RGB);
        try{
            image = ImageIO.read(new File("src/main/resources/dices2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Icon icon = new ImageIcon(image.getSubimage(
                (int) imageCoordinates.get(diceRollResult).getX(),
                (int) imageCoordinates.get(diceRollResult).getY(),
                205, 205));
        rollDiceButton.setIcon(icon);
    }
    public void addButtonsToListOfButtons(){
        buttons.add(this.rollDiceButton);
        buttons.add(this.newGameButton);
    }

    public List<JButton> getListOfButtons(){
        return this.buttons;
    }
}
