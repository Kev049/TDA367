package org.group7;

import org.group7.model.Game;
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
    private JPanel boardPanel;
    private JPanel rightPanel;
    private Box currentTile;
    private List<Point> gamePathTileCoordinates;
    private List<Point> boardTileCoordinates;
    private HashMap<Point, Box> boxPointHashMap;

    public GameWindow(String name, DrawPanel view){
        this.game = new Game();
        this.boxPointHashMap = new HashMap<>();
        this.boardTileCoordinates = new ArrayList<>(121); //List of coordinates for ALL tiles on board
        this.gamePathTileCoordinates = new ArrayList<>(40); //Coordinates for tiles that match game path

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
        for(int y = 0; y < 11; y++){
            c.fill = GridBagConstraints.BOTH;
            c.gridy = y;
            for(int x = 0; x < 11; x++){
                c.gridx = x;
                /*
                This will create a 11x11 grid of boxes of equal size.
                 */
                Box box = new Box(Box.HEIGHT);
                box.setPreferredSize(new Dimension(91, 91));
                box.setBorder(BorderFactory.createLineBorder(Color.black));

                //Save the coordinates for box
                Point coordinate = new Point(x, y);
                this.boardTileCoordinates.add(coordinate);

                //Put the box in hashmap with matching coordinates as key for later use
                boxPointHashMap.put(coordinate, box);
                boardPanel.add(box, c);
            }
        }
    }
    private void initGamePathTileCoordinates(){
        //Add all coordinates which match the "Fia med Knuff" game path in grid.
        //TODO: Find a better solution for this horrible disgusting code
        Collections.addAll(this.gamePathTileCoordinates, boardTileCoordinates.get(44), boardTileCoordinates.get(45),
                boardTileCoordinates.get(46), boardTileCoordinates.get(47), boardTileCoordinates.get(48),
                boardTileCoordinates.get(37), boardTileCoordinates.get(26), boardTileCoordinates.get(15),
                boardTileCoordinates.get(4), boardTileCoordinates.get(5), boardTileCoordinates.get(6),
                boardTileCoordinates.get(17), boardTileCoordinates.get(28), boardTileCoordinates.get(39),
                boardTileCoordinates.get(50), boardTileCoordinates.get(51), boardTileCoordinates.get(52),
                boardTileCoordinates.get(53), boardTileCoordinates.get(54), boardTileCoordinates.get(65),
                boardTileCoordinates.get(76), boardTileCoordinates.get(75), boardTileCoordinates.get(74),
                boardTileCoordinates.get(73), boardTileCoordinates.get(72), boardTileCoordinates.get(83),
                boardTileCoordinates.get(94), boardTileCoordinates.get(105), boardTileCoordinates.get(116),
                boardTileCoordinates.get(115), boardTileCoordinates.get(114), boardTileCoordinates.get(103),
                boardTileCoordinates.get(92), boardTileCoordinates.get(81), boardTileCoordinates.get(70),
                boardTileCoordinates.get(69), boardTileCoordinates.get(68), boardTileCoordinates.get(67),
                boardTileCoordinates.get(66), boardTileCoordinates.get(55));
    }
    private List<Point> getListofgamePathTileCoordinates(){
        return this.gamePathTileCoordinates;
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
        initGamePathTileCoordinates();
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
        List<Point> coordinates = this.gamePathTileCoordinates;
        System.out.println(boxPointHashMap.get(coordinates.get(0)));
        Icon icon = new ImageIcon("src/main/resources/red_player_circle.png");
        JButton piece = new JButton(icon);
        piece.setBorderPainted(false);
        piece.setContentAreaFilled(false);
        piece.setFocusPainted(false);
        piece.setOpaque(false);
        currentPos = 0;
        currentTile = boxPointHashMap.get(coordinates.get(currentPos));
        currentTile.add(piece);
        //Controller
        piece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPos = (currentPos + diceRoll) % 40;
                currentTile.remove(piece);
                currentTile.repaint();
                currentTile.revalidate();
                currentTile = boxPointHashMap.get(coordinates.get(currentPos));
                currentTile.add(piece);
            }
        });
    }
    private void initBoardImg() {
        ImageIcon image1 = new ImageIcon("src/main/resources/Board.png");
        this.add(new JLabel(image1));
    }
}
