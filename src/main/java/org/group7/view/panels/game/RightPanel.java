package org.group7.view.panels.game;

import org.group7.controller.observe.StringObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class RightPanel extends JPanel implements StringObserver {
    private final JButton rollDiceButton;
    private final JLabel playerTurnOutput;

    public RightPanel(JButton rollDiceButton) {
        String startPlayer = "Red";
        this.rollDiceButton = rollDiceButton;
        this.playerTurnOutput = new JLabel( startPlayer + " player's turn");
        this.setLayout(new GridBagLayout());
        initDiceComponents();
    }

    private void initDiceComponents() {
        initPlayerTurnOutputComponent();
        applyDiceComponentDesign();
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 0;
        c.insets = new Insets(0, 0, 550, 0);
        this.add(playerTurnOutput, c);

        c.insets = new Insets(0, 0, 50, 0);
        c.gridy = 1;
        this.add(rollDiceButton, c);
    }

    private void initPlayerTurnOutputComponent() {
        playerTurnOutput.setFont(new Font("Arial", Font.PLAIN, 32));
        FontMetrics fm = playerTurnOutput.getFontMetrics(playerTurnOutput.getFont());
        int w = 275;
        int h = fm.getHeight();
        Dimension size = new Dimension(w, h);
        playerTurnOutput.setMinimumSize(size);
        playerTurnOutput.setPreferredSize(size);
    }

    private void applyDiceComponentDesign() {
        BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
        try {
            URL dicePath = RightPanel.class.getClassLoader().getResource("Dices.png");
            image = ImageIO.read(dicePath);
            //image = ImageIO.read(new File("src/main/resources/Dices.png"));
        } catch (IOException e) {
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
        } else if (s.equals(Color.GREEN.toString())) {
            this.playerTurnOutput.setText("Green player's turn");
        } else if (s.equals(Color.BLUE.toString())) {
            this.playerTurnOutput.setText("Blue player's turn");
        } else if (s.equals(Color.YELLOW.toString())) {
            this.playerTurnOutput.setText("Yellow player's turn");
        }

    }
}
