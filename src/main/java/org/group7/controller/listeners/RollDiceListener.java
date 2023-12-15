package org.group7.controller.listeners;

import org.group7.model.game.Game;
import org.group7.view.panels.menu.DrawMenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class is used to listen to the RollDiceButton.
 * It is used to roll the dice once clicked upon and also handles the animation.
 */
public class RollDiceListener implements ActionListener {
    private final JButton rollDiceButton;
    private int frames = 0;
    private final Random random;
    private BufferedImage image;
    private final List<Point> imageCoordinates = new ArrayList<>();
    private final Game game;

    public RollDiceListener(JButton rollDiceButton, Game game) {
        this.game = game;
        this.image = new BufferedImage(205, 205, BufferedImage.TYPE_INT_RGB);
        this.rollDiceButton = rollDiceButton;
        long seed = System.currentTimeMillis();
        this.random = new Random(seed);
        initDiceImageCoordinates();
        readImage();
    }

    private void initDiceImageCoordinates() {
        Collections.addAll(imageCoordinates, new Point(0, 0), new Point(205, 0),
                new Point(408, 0), new Point(0, 203), new Point(205, 203),
                new Point(408, 203));
    }

    private void readImage() {
        try {
            URL dicePath = DrawMenuPanel.class.getClassLoader().getResource("Dices.png");
            image = ImageIO.read(dicePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void timerDone() {
        int diceRollResult = game.roll() - 1;
        Icon icon = new ImageIcon(image.getSubimage(
                (int) imageCoordinates.get(diceRollResult).getX(),
                (int) imageCoordinates.get(diceRollResult).getY(),
                205, 205));
        rollDiceButton.setIcon(icon);
        rollDiceButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int totalAmountFrames = 6;
        if (++frames >= totalAmountFrames) {
            Timer timer = (Timer) e.getSource();
            timer.stop();
            frames = 0;
            timerDone();
        } else {
            int randomInt = random.nextInt(6);
            Icon icon = new ImageIcon(image.getSubimage(
                    (int) imageCoordinates.get(randomInt).getX(),
                    (int) imageCoordinates.get(randomInt).getY(),
                    205, 205));
            rollDiceButton.setIcon(icon);
        }
    }
}
