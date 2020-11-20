package ui.gui;

import model.ChessGame;

import javax.swing.*;
import java.awt.*;

import static ui.gui.VisualConsole.BOARD_SIZE;

public class PlayerDisplay extends JPanel {
    ChessGame currentGame;
    JLabel player;

    public PlayerDisplay(ChessGame game, boolean whitePlayer) {

        if (whitePlayer) {
            player = new JLabel(game.getWhitePlayer().getName());
        } else {
            player = new JLabel(game.getBlackPlayer().getName());
        }

        this.add(player);
        Dimension squareDim = new Dimension(BOARD_SIZE.width / 2, BOARD_SIZE.height / 2);
        this.setPreferredSize(squareDim);
        this.setVisible(true);
    }

}
