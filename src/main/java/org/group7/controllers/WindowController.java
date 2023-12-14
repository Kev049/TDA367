package org.group7.controllers;

import org.group7.GameWindow;
import org.group7.MenuWindow;
import org.group7.view.DrawGamePanel;
import org.group7.view.DrawMenuPanel;

import javax.swing.*;

public class WindowController {
    private final JButton fourPlayerMenuButton;
    private JButton newGameButton;
    private MenuWindow menuWindow;
    private GameWindow gameWindow;
    private DrawGamePanel drawGamePanel;
    private DrawMenuPanel drawMenuPanel;

    public WindowController(MenuWindow menuWindow, DrawGamePanel drawGamePanel, DrawMenuPanel drawMenuPanel, JButton fourPlayerMenuButton, JButton newGameButton) {
        this.newGameButton = newGameButton;
        this.drawGamePanel = drawGamePanel;
        this.drawMenuPanel = drawMenuPanel;
        this.fourPlayerMenuButton = fourPlayerMenuButton;
        this.menuWindow = menuWindow;
        addListener();
    }

    private void addListener() {
        fourPlayerMenuButton.addActionListener(e -> {
            menuWindow.dispose();
            gameWindow = new GameWindow("TurboFia", drawGamePanel);
        });
        newGameButton.addActionListener(e -> {
            gameWindow.dispose();
            menuWindow = new MenuWindow("TurboFia", drawMenuPanel);
        });
    }
}
