package org.group7.view;

import org.group7.controllers.StringObserver;
import org.group7.model.Game;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.util.List;

public class DrawPanels extends JPanel{
    private final JPanel boardPanel;
    private final JPanel leftPanel;
    private final JPanel rightPanel;

    public DrawPanels(BoardPanel boardPanel, List<JButton> buttons, Game game) {
        this.boardPanel = boardPanel;
        JButton rollDiceButton = buttons.getFirst();   // TODO Utan Java21 funkar inte detta, ändra?
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
}

