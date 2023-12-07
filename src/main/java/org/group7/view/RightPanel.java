package org.group7.view;

import org.group7.controllers.GameController;
import org.group7.controllers.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RightPanel extends JPanel implements Observer {
    JButton rollDiceButton;
    JLabel playerTurnOutput;
    public RightPanel(JButton rollDiceButton){
        this.rollDiceButton = rollDiceButton;
        this.playerTurnOutput = new JLabel("Red player's turn");
        this.setLayout(new GridBagLayout());
        initDiceComponents();
    }

    private void initDiceComponents(){
        initTurnComponent();
        initDiceComponent();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 0;
        c.insets = new Insets(0, 0, 550, 0);
        this.add(playerTurnOutput, c);

        c.insets = new Insets(0,0, 50, 0);
        c.gridy = 1;
        this.add(rollDiceButton, c);
    }

    private void initTurnComponent(){
        playerTurnOutput.setFont(new Font("Arial", Font.PLAIN, 32));
    }

    private void initDiceComponent(){
        BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
        try{
            image = ImageIO.read(new File("src/main/resources/dices2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Icon icon = new ImageIcon(image.getSubimage(0, 0, 205, 205));
        rollDiceButton.setIcon(icon);
        rollDiceButton.setFont(new Font("Arial", Font.PLAIN, 40));
        rollDiceButton.setContentAreaFilled(false);
        rollDiceButton.setBorderPainted(false);
        rollDiceButton.setFocusPainted(false);
    }

    @Override
    public void update(String s) {
        if (s.equals(Color.RED.toString())) {
            this.playerTurnOutput.setText("Red player's turn");
        }
        else if (s.equals(Color.GREEN.toString())) {
            this.playerTurnOutput.setText("Green player's turn");
        }
        else if(s.equals(Color.BLUE.toString())) {
        this.playerTurnOutput.setText("Blue player's turn");
        }
        else if(s.equals(Color.YELLOW.toString())) {
        this.playerTurnOutput.setText("Yellow player's turn");
        }

    }
}
