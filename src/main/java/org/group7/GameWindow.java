package org.group7;

import org.group7.view.DrawBoard;
import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    private static final int X = 700; //currently 2 random sizes for the window
    private static final int Y = 700;

    private DrawBoard drawBoard; //is this fine from an OOP standpoint?

    public GameWindow(String name){
        componentSetup(name);
    }

    private void componentSetup(String title){
        setTitle(title);
        setPreferredSize(new Dimension(X,Y));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        /* add components/views here

        add(drawBoard);

         */

        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centers the frame
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Makes the frame visible
        setVisible(true);
        // Ensures that the window closes when pressing the 'x' button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
