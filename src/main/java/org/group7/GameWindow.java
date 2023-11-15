package org.group7;

import org.group7.model.Game;
import org.group7.view.DrawBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;

    private Game game;

    private DrawBoard drawBoard; //is this fine from an OOP standpoint?

    public GameWindow(String name){//DrawBoard view
        this.game = new Game();
        this.drawBoard = new DrawBoard();
        componentSetup(name);
    }

    private void componentSetup(String title){
        setTitle(title);
        setPreferredSize(new Dimension(X,Y));
        setLayout(null);

        /* add components/views here
         */
        add(drawBoard);


        //JPanel imagePanel = new ImagePanel();
        JButton rollDiceButton = new JButton();
        rollDiceButton.setText("Roll!");
        rollDiceButton.setFont(new Font("Arial", Font.PLAIN, 40));
        rollDiceButton.setBounds(1500, 800, 200, 100);

        this.add(rollDiceButton);

        JTextField diceOutput = new JTextField("");
        diceOutput.setText("You rolled");
        diceOutput.setFont(new Font("Arial", Font.PLAIN, 30));
        diceOutput.setBounds(1500, 700, 200, 80);
        this.add(diceOutput);

        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = game.rollDice();
                diceOutput.setText("You rolled " + diceRoll + "!");
            }
        });




        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
