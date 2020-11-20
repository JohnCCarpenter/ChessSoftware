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

    BufferedImage white = new BufferedImage(100, 100, 1);
    Graphics2D graphics = white.createGraphics();
    BufferedImage black = new BufferedImage(100, 100, 1);
    Graphics2D otherGraphics = black.createGraphics();

    //EFFECTS: creates a chessboard with a given chess game on it
    public ChessBoard(ChessGame cg) {

        game = cg;
        this.setPreferredSize(new Dimension(BOARD_SIZE));
        this.setLayout(boardLayout);
        visualizeBoard();
        this.setVisible(true);

    }

    // Code for board visualization was adjusted from user Andrew Thompson's post on stack overflow
    // https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
    //EFFECTS: Creates a new JPanel overlay with all the active pieces in the right spots
    private void visualizeBoard() {
        ImageIcon piece = new ImageIcon("./data/BlackPawn.png");

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);


                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                Image pieceImage = choosePieceImageOnSquare(ii, jj);

                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                Graphics g = icon.getImage().getGraphics();

                drawImage(pieceImage, g);

                b.setIcon(icon);

                setBackGroundColour(ii, jj, b);
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fills the the chessboard from top left right then down
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                this.add(chessBoardSquares[ii][jj]);
            }
        }
    }

    //EFFECTS: draws an image to the drawer
    private void drawImage(Image pieceImage, Graphics g) {
        if (!(pieceImage == null)) {
            g.drawImage(pieceImage, 0, 0, 64, 64, 0, 0,
                    pieceImage.getWidth(null), pieceImage.getHeight(null), null);
        }
    }

    //EFFECTS: sets the background colour of the square ii jj
    private void setBackGroundColour(int ii, int jj, JButton b) {
        if (!(((ii + jj) % 2) == 1)) {
            b.setBackground(Color.LIGHT_GRAY);
        } else if (((ii + jj) % 2) == 1) {
            b.setBackground(Color.DARK_GRAY);
        }
    }

    //EFFECTS: returns the image of the piece on the square, if null means nothing on
    private BufferedImage choosePieceImageOnSquare(int ii, int jj) {
        ChessPiece cp = game.returnPieceOn(ii, jj);
        if (cp == null) {
            return null;
        } else {
            try {
                return ImageIO.read(new File("./data/BlackPawn.png"));
            } catch (IOException ioe) {
                return null;
            }

//            if (cp.symbol().equals("P")) {
//                //incomplete
//            }
        }


    }
}
