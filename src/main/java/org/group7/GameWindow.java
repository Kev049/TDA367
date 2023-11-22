package org.group7;

import org.group7.model.Game;
import org.group7.model.Piece;
import org.group7.view.DrawBoard;
import org.group7.controllers.RollDiceButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;

    int currentPos = 0;
    int diceroll;

    private Game game;


    private DrawBoard drawBoard; //is this fine from an OOP standpoint?


    private Point[] coordinates = new Point[40];


    public GameWindow(String name, DrawBoard view){//DrawBoard view
        this.game = new Game();
        drawBoard = view;
        initPos();
        componentSetup(name);
    }

    private void initPos(){
        coordinates[0] = new Point(494,410); //START RÖD

        coordinates[1] = new Point(581,410);
        coordinates[2] = new Point(668,410);
        coordinates[3] = new Point(755,410);
        coordinates[4] = new Point(842,410);

        coordinates[5] = new Point(842,323);
        coordinates[6] = new Point(842,236);
        coordinates[7] = new Point(842,149);
        coordinates[8] = new Point(842,62);

        coordinates[9] = new Point(929,62);
        coordinates[10] = new Point(1016,62); //START GRÖN

        coordinates[11] = new Point(1016,149);
        coordinates[12] = new Point(1016,236);
        coordinates[13] = new Point(1016,323);
        coordinates[14] = new Point(1016,410);

        coordinates[15] = new Point(1103,410);
        coordinates[16] = new Point(1190,410);
        coordinates[17] = new Point(1277,410);
        coordinates[18] = new Point(1364,410);

        coordinates[19] = new Point(1364,497);
        coordinates[20] = new Point(1364,584); //START BLÅ

        coordinates[21] = new Point(1277,584);
        coordinates[22] = new Point(1190,584);
        coordinates[23] = new Point(1103,584);
        coordinates[24] = new Point(1016,584);

        coordinates[25] = new Point(1016,671);
        coordinates[26] = new Point(1016,758);
        coordinates[27] = new Point(1016,845);
        coordinates[28] = new Point(1016,932);

        coordinates[29] = new Point(929,932); //START GUL
        coordinates[30] = new Point(842,932);

        coordinates[31] = new Point(842,845);
        coordinates[32] = new Point(842,758);
        coordinates[33] = new Point(842,671);
        coordinates[34] = new Point(842,584);

        coordinates[35] = new Point(755,584);
        coordinates[36] = new Point(668,584);
        coordinates[37] = new Point(581,584);
        coordinates[38] = new Point(494,584);

        coordinates[39] = new Point(494,497);

        //sen nästena, går inte nu ingen märker ingen kommer orka
    }

    private void componentSetup(String title){
        setTitle(title);
        setPreferredSize(new Dimension(X,Y));

        /* add components/views here
         */
        //add(drawBoard); //Maybe not needed?

        add(new RollDiceButton());
        initNewGameButton();
        initPieces();
        initBoardImg();
        //initHelpButtons();

        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /*

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

        //Flytta till en controller klass
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceRoll = game.rollDice();
                diceOutput.setText("You rolled " + diceRoll + "!");
            }
        });
    }
    */

    //Flytta till en controller klass
    private void initNewGameButton() {
        JButton newGameBtn = new JButton();
        newGameBtn.setText("Start New Game");
        newGameBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        newGameBtn.setBounds(50, 50, 280, 80);

        this.add(newGameBtn);

        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Implement functionality
            }
        });
    }

    /*
    private void initHelpButtons() {
        JButton helpButton1 = new JButton();
        JButton helpButton2 = new JButton();
        JButton helpButton3 = new JButton();
        JButton helpButton4 = new JButton();
        JButton helpButton5 = new JButton();
        helpButton1.setText("Start New Game");
        helpButton1.setFont(new Font("Arial", Font.PLAIN, 30));
        helpButton1.setBounds(50, 50, 280, 80);

        this.add(helpButton1);

        helpButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Implement functionality
            }
        });
    }
    */

    private void initPieces(){
        //494, 581
        //410
        int x = 494;
        int y = 410;
        Icon icon = new ImageIcon("src/main/resources/red_player_circle.png");
        JButton piece = new JButton(icon);
        piece.setBorderPainted(false);
        piece.setContentAreaFilled(false);
        piece.setFocusPainted(false);
        piece.setOpaque(false);
        piece.setBounds(x, y, 45, 45);
        this.add(piece);

        //Controller
        piece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPos += diceRoll;
                int newPosX = (int) (coordinates[currentPos]).getX();
                int newPosY = (int) (coordinates[currentPos]).getY();
                piece.setBounds(newPosX, newPosY, 45, 45);
                System.out.println(newPosX);
            }
        });

        // repaint();
    }


   /* private colour drawPieces{

    }*/

    private void initBoardImg() {
        ImageIcon image1 = new ImageIcon("src/main/resources/Board.png");
        this.add(new JLabel(image1));
    }
}
