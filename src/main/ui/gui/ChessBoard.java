package ui.gui;

import jdk.nashorn.internal.objects.NativeString;
import model.ChessGame;
import model.ChessPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

import static ui.gui.VisualConsole.BOARD_SIZE;

public class ChessBoard extends JPanel {
    //private static final String COLS = "ABCDEFGH";

    private GridLayout boardLayout = new GridLayout(0, 8);
    private ChessGame game;
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private VisualConsole buttonListener;
    int currentIndexX;
    int currentIndexY;

    //EFFECTS: creates a chessboard with a given chess game on it
    public ChessBoard(ChessGame cg, VisualConsole vc) {

        buttonListener = vc;
        game = cg;
        setPreferredSize(new Dimension(BOARD_SIZE));
        setLayout(boardLayout);
        visualizeBoard();
        setVisible(true);

    }

    //EFFECTS: paints the components of this Chessboard to the correct places
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
    }

    // Code for board visualization was adjusted from user Andrew Thompson's post on stack overflow
    // https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
    //EFFECTS: Creates a new JPanel overlay with all the active pieces in the right spots
    public void visualizeBoard() {
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                currentIndexX = ii;
                currentIndexY = jj;
                JButton b = makeButton(buttonMargin, ii, jj);

                if (!(game.returnPieceOn(ii, jj) == null)) {
                    Image i = getScaledImage(game.returnPieceOn(ii, jj).image().getImage(), 85, 88);
                    ImageIcon pieceIcon = new ImageIcon(i);
                    b.setIcon(pieceIcon);
                }

                setBackGroundColour(ii, jj, b);
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fills the the chessboard from bottom left right then up
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                this.add(chessBoardSquares[7 - ii][jj]);
            }
        }
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

    //EFFECTS: creates a button
    public JButton makeButton(Insets buttonMargin, int ii, int jj) {
        ChessSquare b = new ChessSquare();
        b.setMargin(buttonMargin);
        b.addActionListener(buttonListener);
        b.addGridPosition(ii, jj);
        b.setCg(game);
        b.setActionCommand("Select");
        return b;
    }

    //GETTERS AND SETTERS
    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }

    public JButton[][] getChessBoardSquares() {
        return chessBoardSquares;
    }

    public void setChessBoardSquares(JButton[][] chessBoardSquares) {
        this.chessBoardSquares = chessBoardSquares;
    }

    public VisualConsole getButtonListener() {
        return buttonListener;
    }

    public void setButtonListener(VisualConsole buttonListener) {
        this.buttonListener = buttonListener;
    }

    public int getCurrentIndexX() {
        return currentIndexX;
    }

    public void setCurrentIndexX(int currentIndexX) {
        this.currentIndexX = currentIndexX;
    }

    public int getCurrentIndexY() {
        return currentIndexY;
    }

    public void setCurrentIndexY(int currentIndexY) {
        this.currentIndexY = currentIndexY;
    }

    //EFFECTS: sets the background colour of the square ii jj
    private void setBackGroundColour(int ii, int jj, JButton b) {
        if (!(((ii + jj) % 2) == 1)) {
            b.setBackground(Color.WHITE);
        } else if (((ii + jj) % 2) == 1) {
            b.setBackground(Color.DARK_GRAY);
        }
    }

    //GETTERS AND SETTERS

}
