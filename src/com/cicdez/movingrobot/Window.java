package com.cicdez.movingrobot;

import javax.swing.*;

public class Window extends JFrame {
    public final Board board;

    public Window(Board board, Screen screen, String algorithmName) {
        super("Moving Robot - " + algorithmName);

        this.board = board;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(screen);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
