package org.group7.view.panels.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//This panel will have the new game button and the powerup info

public class LeftPanel extends JPanel {
    private final JPanel powerUpFrame;
    private final List<JButton> powerUpButtons;

    //PowerUp buttons
    private final JButton basePowerUp;
    private final JButton catapultPowerUp;
    private final JButton laserPowerUp;
    private final JButton lightningPowerUp;
    private final JButton switchPowerUp;

    //Game button
    private final JButton newGameButton;

    public LeftPanel() {
        this.setLayout(new GridBagLayout());
        this.powerUpButtons = new ArrayList<>();
        this.powerUpFrame = new JPanel();
        this.basePowerUp = new JButton();
        this.catapultPowerUp = new JButton();
        this.laserPowerUp = new JButton();
        this.lightningPowerUp = new JButton();
        this.switchPowerUp = new JButton();
        Collections.addAll(powerUpButtons, basePowerUp, catapultPowerUp, laserPowerUp, lightningPowerUp, switchPowerUp);
        this.newGameButton = new JButton();
        initComponents();
    }

    private void initComponents() {
        initPowerUpButtons();
        initNewGameButton();

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.gridy = 0;
        c.insets = new Insets(0, 0, 250, 0);
        this.add(newGameButton, c);

        c.insets = new Insets(0, 0, 50, 0);
        c.gridy = 1;
        this.add(powerUpFrame, c);

    }

    public void initNewGameButton() {
        newGameButton.setText("New Game");
        newGameButton.setFont(new Font("Monospaced", Font.PLAIN, 30));
    }

    private void initPowerUpButtons() {
        powerUpFrame.setLayout(new GridBagLayout());
        powerUpFrame.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        int i = 0;
        for (JButton powerUpButton : powerUpButtons) {
            c.gridy = i;
            c.gridx = 0;
            c.insets = new Insets(0, 0, 25, 0);
            powerUpButton.setContentAreaFilled(false);
            powerUpButton.setBorderPainted(false);
            powerUpButton.setFocusPainted(false);
            powerUpFrame.add(powerUpButton, c);

            c.gridx = 1;
            c.insets = new Insets(0, 100,25, 0);
            JLabel questionMark = new JLabel();
            questionMark.setIcon(new ImageIcon(getImage("powerups/Question_mark.png")));
            powerUpFrame.add(questionMark, c);
            i++;
        }

        basePowerUp.setToolTipText("Takes a piece out of base");
        catapultPowerUp.setToolTipText("To be implemented");
        laserPowerUp.setToolTipText("Blasts all powerups and pieces 8 tiles in front of the piece");
        lightningPowerUp.setToolTipText("Move forward 2 steps");
        switchPowerUp.setToolTipText("To be implemented");

        basePowerUp.setIcon(new ImageIcon(getImage("powerups/Base_icon.png")));
        catapultPowerUp.setIcon(new ImageIcon(getImage("powerups/Catapult_icon.png")));
        laserPowerUp.setIcon(new ImageIcon(getImage("powerups/Laser_icon.png")));
        lightningPowerUp.setIcon(new ImageIcon(getImage("powerups/Lightning_icon.png")));
        switchPowerUp.setIcon(new ImageIcon(getImage("powerups/Switch_icon.png")));
    }

    private Image getImage(String path) {
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        try {
            URL powerUpPath = LeftPanel.class.getClassLoader().getResource(path);
            image = ImageIO.read(powerUpPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public JButton getNewGameButton() {
        return this.newGameButton;
    }
}
