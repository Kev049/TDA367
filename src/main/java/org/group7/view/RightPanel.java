package org.group7.view;

import org.group7.controllers.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RightPanel extends JPanel{
    JButton rollDiceButton;
    public RightPanel(JButton rollDiceButton){
        this.rollDiceButton = rollDiceButton;
        this.setLayout(new GridBagLayout());
        initDiceComponents();
    }

    private void initDiceComponents(){
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 0;
        c.insets = new Insets(0, 0, 560, 0);
        JLabel playerTurnOutput = new JLabel("Red player's turn");
        playerTurnOutput.setFont(new Font("Arial", Font.PLAIN, 32));
        this.add(playerTurnOutput, c);

        c.insets = new Insets(0,0, 50, 0);
        c.gridy = 1;
        JTextField diceOutput = new JTextField("");
        diceOutput.setEditable(false);
        diceOutput.setPreferredSize(new Dimension(200, 100));
        diceOutput.setText("");
        diceOutput.setFont(new Font("Arial", Font.PLAIN, 30));
        this.add(diceOutput, c);

        c.gridy = 2;
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
        this.add(rollDiceButton, c);
    }
}
