package org.group7.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//This panel will have the new game button and the powerup info

public class LeftPanel extends JPanel {

    JPanel powerUpFrame;
    List<JButton> powerUpButtons;

    //PowerUp buttons
    JButton basePowerUp;
    JButton catapultPowerUp;
    JButton laserPowerUp;
    JButton lightningPowerUp;
    JButton switchPowerUp;

    //Game button
    JButton newGameButton;

    public LeftPanel(){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.RED);
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

    private void initComponents(){
        initPowerUpButtons();
        initNewGameButton();

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.gridy = 0;
        c.insets = new Insets(0, 0, 350, 0);
        this.add(newGameButton, c);

        c.insets = new Insets(0,0, 50, 0);
        c.gridy = 1;
        this.add(powerUpFrame, c);

    }

    public void initNewGameButton(){
        newGameButton.setText("New Game");
        newGameButton.setFont(new Font("Monospaced", Font.PLAIN, 30));
    }

    private void initPowerUpButtons(){
        powerUpFrame.setLayout(new GridBagLayout());
        powerUpFrame.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        int i = 0;
        for(JButton powerUpButton : powerUpButtons){
            c.gridy = i;
            c.insets = new Insets(0, 0, 25, 0);
            powerUpButton.setContentAreaFilled(false);
            powerUpButton.setBorderPainted(false);
            powerUpButton.setFocusPainted(false);
            powerUpFrame.add(powerUpButton, c);
            i++;
        }

        basePowerUp.setIcon(new ImageIcon(getImage("src/main/resources/Base_icon.png")));
        catapultPowerUp.setIcon(new ImageIcon(getImage("src/main/resources/Catapult_icon.png")));
        laserPowerUp.setIcon(new ImageIcon(getImage("src/main/resources/Laser_icon.png")));
        lightningPowerUp.setIcon(new ImageIcon(getImage("src/main/resources/Lightning_icon.png")));
        switchPowerUp.setIcon(new ImageIcon(getImage("src/main/resources/Switch_icon.png")));
    }

    private Image getImage(String path){
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        try{
            image = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
