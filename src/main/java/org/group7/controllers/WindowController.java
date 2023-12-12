package org.group7.controllers;

import org.group7.GameWindow;
import org.group7.MenuWindow;
import org.group7.view.DrawGamePanel;

import javax.swing.*;

public class WindowController {
    private final JButton fourPlayerMenuButton;
    private MenuWindow menuWindow;
    private DrawGamePanel drawPanel;
    public WindowController(MenuWindow menuWindow, DrawGamePanel drawPanel, JButton fourPlayerMenuButton){
        this.drawPanel = drawPanel;
        this.fourPlayerMenuButton = fourPlayerMenuButton;
        this.menuWindow = menuWindow;
        addListener();
    }

    private void addListener(){
        fourPlayerMenuButton.addActionListener(e -> {
            menuWindow.dispose();
            new GameWindow("TurboFia", drawPanel);
        });
    }
}
