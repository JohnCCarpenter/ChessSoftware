package gui;

import model.ChessGame;
import model.Translator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static gui.VisualConsole.BOARD_SIZE;

public class ChessBoard extends JPanel {
    private GridLayout boardLayout = new GridLayout(8,8);
    private ChessGame game;

    BufferedImage white = new BufferedImage(100,100, 1);
    Graphics2D graphics = white.createGraphics();
    BufferedImage black = new BufferedImage(100,100, 1);
    Graphics2D otherGraphics = black.createGraphics();

    //EFFECTS: creates a chessboard with a given chess game on it
    public ChessBoard(ChessGame cg) {

        game = cg;
        this.setPreferredSize(new Dimension(BOARD_SIZE));
        //createSquareButtons();
        this.setLayout(boardLayout);
        createCheckers();
        //visualizePieces();
        //this.pack();
        //this.setSize(5, 5);
        this.setVisible(true);

    }

    //EFFECTS: Creates a new JPanel overlay with all the active pieces in the right spots
    private JLabel[][] visualizePieces() {
        int i = 8;
        int j = 8;
        JLabel[][] pieceOverlay = new JLabel[i][j];

        ImageIcon piece = new ImageIcon("./data/BlackPawn");
        for (; i > 0; i--) {
            for (; j > 0; j--) {
                Dimension squareDim = new Dimension(BOARD_SIZE.width / 8, BOARD_SIZE.height / 8);
                if (!(game.returnPieceOn(i - 1, j - 1) == null)) {
                    pieceOverlay[i - 1][j - 1] = new JLabel(piece);
                    pieceOverlay[i - 1][j - 1].setPreferredSize(squareDim);
                } else {
                    pieceOverlay[i - 1][j - 1] = new JLabel();
                    pieceOverlay[i - 1][j - 1].setPreferredSize(squareDim);
                }
                add(pieceOverlay[i - 1][j - 1]);
            }
        }

        return pieceOverlay;
    }

    private void createCheckers() {
        graphics.setPaint(new Color(0, 0, 0));
        graphics.fillRect(0, 0, white.getWidth(), white.getHeight());
        graphics.dispose();
        ImageIcon blackIcon = new ImageIcon(black);

        otherGraphics.setPaint(new Color(255, 255, 255));
        otherGraphics.fillRect(0, 0, black.getWidth(), black.getHeight());
        otherGraphics.dispose();
        ImageIcon whiteIcon = new ImageIcon(white);

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                JButton temp;
                if (((i + j) % 2) == 1) {
                    temp = new JButton(blackIcon);

                } else {
                    temp = new JButton(whiteIcon);
                }
                Dimension squareDim = new Dimension(BOARD_SIZE.width / 8, BOARD_SIZE.height / 8);
                temp.setPreferredSize(squareDim);
                this.add(temp);

            }
        }
    }

    private void createSquareButtons() {
        ImageIcon horse = new ImageIcon("./data/UndefinedEngineBlackFront.jpg");
        //code to convert IconImage to Buffered image found from user Werner Kvalem Vesteras on stackoverflow
        BufferedImage bi = new BufferedImage(horse.getIconWidth(), horse.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.dispose();
        ImageIcon newHorse = new ImageIcon(getScaledImage(bi, 500, 500));

//        JButton b1 = new JButton(horse);
//        JButton b2 = new JButton("b2");
//        JButton a1 = new JButton("a1");
//        JButton a2 = new JButton(horse);
//
//        this.add(b1);
//        this.add(b2);
//        this.add(a1);
//        this.add(a2);
    }

    //Code taken from user Suken Shah on stackoverflow
    //EFFECTS: rescales an imput image to the desired size
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
