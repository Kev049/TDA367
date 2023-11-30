package org.group7.controllers;

import org.group7.model.Game;
import org.group7.model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class PieceButton extends JButton{
    private Piece piece;

    public PieceButton(Piece piece, int x, int y) {
        super(new ImageIcon("src/main/resources/" + piece.getColor().toString().toLowerCase() + "_player_circle.png"));
        this.setBtnProperties(x, y);
        this.piece = piece;

        //Controller
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*piece.move(diceRoll);
                pieceBtn.setBounds(newPosX, newPosY, 45, 45);*/
            }
        });
    }

    public void setBtnProperties(int x, int y) {
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setBounds(x, y, 45, 45);
    }
}