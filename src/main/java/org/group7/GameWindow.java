package org.group7;

import org.group7.model.Game;
import org.group7.view.DrawBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;

    private Game game;

    private DrawBoard drawBoard; //is this fine from an OOP standpoint?

    public GameWindow(String name, DrawBoard view){//DrawBoard view
        this.game = new Game();
        drawBoard = view;
        componentSetup(name);
    }

    private void componentSetup(String title){
        setTitle(title);
        setPreferredSize(new Dimension(X,Y));

        /* add components/views here
         */
        //add(drawBoard); //Maybe not needed?

        initDiceRollComponents();
        initBoardImg();

        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = game.rollDice();
                diceOutput.setText("You rolled " + diceRoll + "!");
            }
        });
    }

    private void initBoardImg() {
        ImageIcon image1 = new ImageIcon("C:\\Users\\kevin\\Desktop\\oopp\\TurboFia\\src\\main\\java\\org\\group7\\Board.png");
        this.add(new JLabel(image1));
    }
}
