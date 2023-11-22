package org.group7;

import org.group7.model.Game;
import org.group7.model.Piece;
import org.group7.model.Tile;
import org.group7.view.DrawBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;

    int diceRoll;
    int currentPos = 0;

    private Game game;

    private DrawBoard drawBoard; //is this fine from an OOP standpoint?

    private JPanel container;

    private JPanel leftPanel;
    private JPanel boardPanel;
    private JPanel rightPanel;

    public GameWindow(String name, DrawBoard view){
        this.game = new Game();
        drawBoard = view;
        componentSetup(name);
    }

    private void createPanels(){
        container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        this.add(container);

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());
        c.weightx = 0.45;
        c.gridy = 0;
        c.gridx = 0;
        container.add(leftPanel, c);
        leftPanel.setBackground(Color.RED);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        boardPanel.setBackground(Color.GRAY);
        c.weightx = 0.1;
        c.gridx = 1;
        container.add(boardPanel, c);
        drawTiles(boardPanel);

        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        rightPanel.setBackground(Color.BLUE);
        c.weightx = 0.45;
        c.gridx = 2;
        container.add(rightPanel, c);
    }

    private void drawTiles(JPanel boardPanel){
        GridBagConstraints c = new GridBagConstraints();
        for(int x = 0; x < 11; x++){
            c.fill = GridBagConstraints.BOTH;
            c.gridy = x;
            for(int y = 0; y < 11; y++){
                c.gridx = y;
                Box box = new Box(Box.HEIGHT);
                box.setPreferredSize(new Dimension(91, 91));
                box.setBorder(BorderFactory.createLineBorder(Color.black));
                boardPanel.add(box, c);
            }
        }
    }

    private Point[] getListofTileCoordinates(){
        Point[] tileCoordinates = {new Point(4, 0), new Point(4,1), new Point(4,2),
                new Point(4,3), new Point(4,4), new Point(3,4), new Point(2,4),
                new Point(1,4), new Point(0,4), new Point(0,5), new Point(0,6),
                new Point(1,6), new Point(2,6), new Point(3,6), new Point(4,6),
                new Point(4,7), new Point(4,8), new Point(4,9), new Point(4, 10),
                new Point(5,10), new Point(6,10), new Point(6,9), new Point(6, 8),
                new Point(6,7), new Point(6,6), new Point(7,6), new Point(8,6),
                new Point(9, 6), new Point(10,6), new Point(10,5), new Point(10,4),
                new Point(9,4), new Point(8,4), new Point(7,4), new Point(6,4),
                new Point(6,3), new Point(6,2), new Point(6,1), new Point(6,0),
                new Point(5, 0)};
        return tileCoordinates;
    }

    private void componentSetup(String title){
        setTitle(title);
        setPreferredSize(new Dimension(X,Y));

        /* add components/views here
         */
        //add(drawBoard); //Maybe not needed?

        initDiceRollComponents();
        initNewGameButton();
        initPieces();
        initBoardImg();

        createPanels();
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

        //Flytta till en controller klass
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceRoll = game.rollDice();
                diceOutput.setText("You rolled " + diceRoll + "!");
            }
        });
    }

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


    private void initPieces(){
        int x = 475;
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
        /*
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
        */

        // repaint();
    }
    private void initBoardImg() {
        ImageIcon image1 = new ImageIcon("src/main/resources/Board.png");
        this.add(new JLabel(image1));
    }
}
