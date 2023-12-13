package org.group7.view;

import org.group7.controllers.StringObserver;
import org.group7.model.Game;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.util.List;

public class DrawGamePanel extends JPanel{
    private final JPanel boardPanel;
    private final JPanel leftPanel;
    private final JPanel rightPanel;

    public DrawGamePanel(BoardPanel boardPanel, Game game, JPanel leftPanel, JPanel rightPanel) {
        this.boardPanel = boardPanel;
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
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

