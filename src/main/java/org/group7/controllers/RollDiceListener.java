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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;

public class RollDiceListener implements ActionListener {
        private JButton rollDiceButton;
        private int frames = 0;
        private int randomInt = 0;
        private Random random;
        private BufferedImage image;
        private List<Point> imageCoordinates = new ArrayList<>();
        private Game game;
        private final int totalAmountFrames = 12;
    public RollDiceListener(JButton rollDiceButton, Game game){
        this.game = game;
        this.image = new BufferedImage(205, 205, BufferedImage.TYPE_INT_RGB);
        this.rollDiceButton = rollDiceButton;
        long seed = System.currentTimeMillis();
        this.random = new Random(seed);
        initDiceImageCoordinates();
        readImage();
    }

    private void initDiceImageCoordinates(){
        Collections.addAll(imageCoordinates, new Point(0, 0), new Point(205, 0),
                new Point(408, 0), new Point(0, 203), new Point(205, 203),
                new Point(408, 203));
    }

    private void readImage(){
        try{
            image = ImageIO.read(new File("src/main/resources/dices2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void timerDone(){
        int diceRollResult = game.rollDice() - 1;
        Icon icon = new ImageIcon(image.getSubimage(
                (int) imageCoordinates.get(diceRollResult).getX(),
                (int) imageCoordinates.get(diceRollResult).getY(),
                205, 205));
        rollDiceButton.setIcon(icon);
        rollDiceButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(++frames >= totalAmountFrames){
            Timer timer = (Timer) e.getSource();
            timer.stop();
            frames = 0;
            timerDone();
        }
        else{
            randomInt = random.nextInt(6);
            Icon icon = new ImageIcon(image.getSubimage(
                    (int) imageCoordinates.get(randomInt).getX(),
                    (int) imageCoordinates.get(randomInt).getY(),
                    205, 205));
            rollDiceButton.setIcon(icon);
        }
    }

}
