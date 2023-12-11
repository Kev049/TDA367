package org.group7.view;

import org.group7.controllers.StringObserver;
import org.group7.model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.List;


//public class DrawPanel extends JPanel implements Observer {
public class DrawPanels extends JPanel{
    private Set<PaintablePiece> pieces; //TODO: Fixa så att vi tar denna data antingen från board eller tiles och sätt ut dem.
    private JPanel boardPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton rollDiceButton;

    public DrawPanels(BoardPanel boardPanel, List<JButton> buttons, Game game) {
        this.boardPanel = boardPanel;
        this.rollDiceButton = buttons.get(0);
        this.leftPanel = new LeftPanel();
        this.rightPanel = new RightPanel(rollDiceButton);
        this.setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1920, 1080));
        drawPanels();
        game.addObserver((StringObserver)rightPanel); //TODO bort med denna
    }

    private void drawPanels(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        //leftpanel
        c.weightx = 0.45;
        c.gridy = 0;
        c.gridx = 0;
        this.add(leftPanel, c);

        //boardpanel
        c.weightx = 0.1;
        c.gridx = 1;
        this.add(boardPanel, c);

        //rightpanel
        c.weightx = 0.45;
        c.gridx = 2;
        this.add(rightPanel, c);
    }
    /*
    @Override
    public void update() {
        repaint();
    }
     */
}

