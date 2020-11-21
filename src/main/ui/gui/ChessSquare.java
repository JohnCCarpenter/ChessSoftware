package ui.gui;

import model.ChessGame;

import javax.swing.*;

public class ChessSquare extends JButton {
    int posX;
    int posY;
    ChessGame cg;

    public ChessGame getCg() {
        return cg;
    }

    public void setCg(ChessGame cg) {
        this.cg = cg;
    }

    public void addGridPosition(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
