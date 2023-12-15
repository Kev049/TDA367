package org.group7.controller;

import org.group7.view.GameWindow;
import org.group7.view.MenuWindow;
import org.group7.view.panels.game.DrawGamePanel;
import org.group7.view.panels.menu.DrawMenuPanel;

import javax.swing.*;

/*
 * This class is responsible for controlling the windows of the application
 * and switching between the start menu and the actual game window.
 */
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
