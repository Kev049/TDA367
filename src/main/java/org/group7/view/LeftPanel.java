package org.group7.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {

    //this panel will have the new game button and the powerup info

    public LeftPanel(){
        this.setLayout(new FlowLayout());
        this.setBackground(Color.RED);
        //initHelpButtons();
        initNewGameButton();
    }

    public void initNewGameButton(){
        JButton newGameButton = new JButton();
        newGameButton.setText("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 30));
        this.add(newGameButton);
    }

    private void initHelpButtons() {
        JButton helpButton1 = new JButton();
        JButton helpButton2 = new JButton();
        JButton helpButton3 = new JButton();
        JButton helpButton4 = new JButton();
        JButton helpButton5 = new JButton();
//        helpButton1.setText("Start New Game");
//        helpButton1.setFont(new Font("Arial", Font.PLAIN, 30));
        helpButton1.setBounds(50, 50, 280, 80);

        this.add(helpButton1);

        helpButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Implement functionality
            }
        });
    }

}
