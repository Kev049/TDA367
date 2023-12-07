package org.group7.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//This panel will have the new game button and the powerup info

public class LeftPanel extends JPanel {

    JFrame powerUpFrame;
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
        this.setLayout(new FlowLayout());
        this.setBackground(Color.RED);
        this.powerUpButtons = new ArrayList<>();
        this.powerUpFrame = new JFrame();
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
        initHelpButtons();
        initNewGameButton();
        this.add(newGameButton);
    }

    public void initNewGameButton(){
        newGameButton.setText("New Game");
        newGameButton.setFont(new Font("Monospaced", Font.PLAIN, 30));
    }

    private void initHelpButtons(){
        for(JButton powerUpButton : powerUpButtons){

        }
        powerUpFrame.add(basePowerUp);
        powerUpFrame.add(catapultPowerUp);
        powerUpFrame.add(laserPowerUp);
        powerUpFrame.add(lightningPowerUp);
        powerUpFrame.add(switchPowerUp);
    }
}
