package org.group7.view.panels.game;

import javax.swing.*;
import java.awt.*;

public class DrawGamePanel extends JPanel {
    private final JPanel boardPanel;
    private final JPanel leftPanel;
    private final JPanel rightPanel;

    public DrawGamePanel(BoardPanel boardPanel, JPanel leftPanel, JPanel rightPanel) {
        this.boardPanel = boardPanel;
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1920, 1080));
        drawPanels();
    }

    private void drawPanels() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        //Left panel
        c.weightx = 0.45;
        c.gridy = 0;
        c.gridx = 0;
        this.add(leftPanel, c);

        //Board panel
        c.weightx = 0.1;
        c.gridx = 1;
        this.add(boardPanel, c);

        //Right panel
        c.weightx = 0.45;
        c.gridx = 2;
        this.add(rightPanel, c);
    }
}

