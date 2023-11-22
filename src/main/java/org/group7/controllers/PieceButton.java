package org.group7.controllers;

import org.group7.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class PieceButton extends JButton{
    public PieceButton(){
        //494, 581
        //410
        int x = 494;
        int y = 410;
        Icon icon = new ImageIcon("src/main/resources/red_player_circle.png");
        JButton piece = new JButton(icon);
        piece.setBorderPainted(false);
        piece.setContentAreaFilled(false);
        piece.setFocusPainted(false);
        piece.setOpaque(false);
        piece.setBounds(x, y, 45, 45);
        this.add(piece);

        //Controller
        piece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece.move_piece(diceRoll);     //koppla så att den fattar vilken knapp man trycker på
                piece.setBounds(newPosX, newPosY, 45, 45);
                System.out.println(newPosX);
            }
        });

        // repaint();
    }
}