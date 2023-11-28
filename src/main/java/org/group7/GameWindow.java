package org.group7;

import org.group7.model.Game;
import org.group7.view.PaintableBoard;
import org.group7.view.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.List;

public class GameWindow extends JFrame{
    private static final int X = 1920;
    private static final int Y = 1080;

    int diceRoll;
    int currentPos = 0;
    private Game game;

    private DrawPanel drawBoard; //is this fine from an OOP standpoint?

    private JPanel container;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private Box currentTile;
    private List<Point> gamePathTileCoordinates;
    private List<Point> boardTileCoordinates;
    private HashMap<Point, Box> boxPointHashMap;
    private PaintableBoard paintableBoard;

    public GameWindow(String name, DrawPanel view, PaintableBoard paintableBoard){
        this.game = new Game();
        this.paintableBoard = paintableBoard;
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

        c.weightx = 0.1;
        c.gridx = 1;
        container.add(paintableBoard, c);

        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        rightPanel.setBackground(Color.BLUE);
        c.weightx = 0.45;
        c.gridx = 2;
        container.add(rightPanel, c);
    }

    private void componentSetup(String title){
        setTitle(title);
        setPreferredSize(new Dimension(X,Y));

        /* add components/views here
         */
        //add(drawBoard); //Maybe not needed?

        initDiceRollComponents();
        initNewGameButton();
        createPanels();
        //initGamePathTileCoordinates();
        initPieces();
        //initBoardImg();

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
        List<Integer> index = paintableBoard.getGamePathTileIndexes();
        HashMap<Integer, Box> indexBoxHashMap = paintableBoard.getIndexBoxHashMap();
        Icon icon = new ImageIcon("src/main/resources/red_player_circle.png");
        JButton piece = new JButton(icon);
        piece.setBorderPainted(false);
        piece.setContentAreaFilled(false);
        piece.setFocusPainted(false);
        piece.setOpaque(false);
        currentPos = 0;
        currentTile = indexBoxHashMap.get(index.get(currentPos));
        currentTile.add(piece);
        //Controller
        piece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add result from dice roll to current position and move around without index out of length of array
                currentPos = (currentPos + diceRoll) % 40;

                //Remove piece from current tile it is on
                currentTile.remove(piece);
                currentTile.repaint();
                currentTile.revalidate();

                //Get tile from the new Point given and add the piece to the tile it has moved to
                currentTile = indexBoxHashMap.get(index.get(currentPos));
                currentTile.add(piece);
            }
        });
    }
    private void initBoardImg() {
        ImageIcon image1 = new ImageIcon("src/main/resources/Board.png");
        this.add(new JLabel(image1));
    }
}
