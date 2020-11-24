package ui.gui;

import model.ChessGame;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static ui.gui.VisualConsole.BOARD_SIZE;

// Displays a player in a chess game, showing their name, captured pieces, colour, and
public class PlayerDisplay extends JPanel {
    ChessGame currentGame;
    JLabel player;
    JPanel captures;
    JLabel points;

    public PlayerDisplay(ChessGame game, boolean whitePlayer) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setAlignmentY(Component.TOP_ALIGNMENT);

        generateComponents(game, whitePlayer);

        setVisible(true);
    }

    //EFFECTS: generates and adds the components of this PlayerDisplay to it
    public void generateComponents(ChessGame game, boolean whitePlayer) {
        playerSpecifics(game, whitePlayer);
        points = pointDisplay(game, whitePlayer);
        alignComponents();

        add(player);
        add(points);
        add(captures);

        Dimension squareDim = new Dimension(BOARD_SIZE.width / 2, BOARD_SIZE.height / 2);
        setPreferredSize(squareDim);
        setMinimumSize(squareDim);
        setMaximumSize(squareDim);
    }

    //EFFECTS: Generates those elements of the player display that are specific to being white/black
    private void playerSpecifics(ChessGame game, boolean whitePlayer) {
        if (whitePlayer) {
            player = new JLabel(game.getWhitePlayer().getName());
            player.setFont(new Font("Times New Roman", Font.BOLD, 45));
            player.setForeground(Color.BLACK);
            captures = visualizeCaptures(game.getWhitePlayer().getCaptured());
            captures.setBackground(Color.WHITE);
            setBackground(Color.WHITE);
        } else {
            player = new JLabel(game.getBlackPlayer().getName());
            player.setFont(new Font("Times New Roman", Font.BOLD, 45));
            player.setForeground(Color.WHITE);
            captures = visualizeCaptures(game.getBlackPlayer().getCaptured());
            captures.setBackground(Color.BLACK);
            setBackground(Color.BLACK);
        }
    }

    //EFFECTS: aligns the components in this frame
    private void alignComponents() {
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        captures.setAlignmentX(Component.CENTER_ALIGNMENT);
        points.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    //EFFECTS: returns the component that shows how many points up the player is (or show nothing if they are down)
    private JLabel pointDisplay(ChessGame game, boolean whitePlayer) {
        int pointsUp = pointsUp(game, whitePlayer);
        JLabel display = new JLabel();
        if (pointsUp > 0) {
            String text = "+" + pointsUp;
            display.setText(text);
        }
        display.setFont(new Font("Times New Roman", Font.BOLD, 24));
        if (whitePlayer) {
            display.setForeground(Color.BLACK);
        } else {
            display.setForeground(Color.WHITE);
        }
        return display;
    }

    //EFFECTS: Returns how many points up white/black player is (depend on whiteplayer input) in the game
    private int pointsUp(ChessGame game, boolean whitePlayer) {
        int pointsUp = 0;

        if (whitePlayer) {
            for (ChessPiece p : game.getWhitePlayer().getCaptured()) {
                pointsUp += p.getValue();
            }
            for (ChessPiece p : game.getBlackPlayer().getCaptured()) {
                pointsUp -= p.getValue();
            }
        } else {
            for (ChessPiece p : game.getWhitePlayer().getCaptured()) {
                pointsUp -= p.getValue();
            }
            for (ChessPiece p : game.getBlackPlayer().getCaptured()) {
                pointsUp += p.getValue();
            }
        }
        return pointsUp;
    }

    private JPanel visualizeCaptures(ArrayList<ChessPiece> captured) {
        JPanel captureDisplay = new JPanel();
        for (ChessPiece p : captured) {
            Image image = getScaledImage(p.image().getImage(), 40, 40);
            ImageIcon icon = new ImageIcon(image);
            //need to set background to transparent!!!
            captureDisplay.add(new JLabel(icon));
        }
        return captureDisplay;
    }

    //Taken from stackoverflow user suken shah
    //https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    //EFFECTS: returns a scaled version of the given Image
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
