package ui.gui;

import model.ChessGame;
import model.Translator;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static ui.TextConsole.DEFAULT_FILE;

//Displays the entirety of the chess game GUI
public class VisualConsole extends JFrame implements ActionListener {
    private static final Point CENTRE_SCREEN = new Point(400, 150);
    public static final Dimension BOARD_SIZE = new Dimension(700, 700);


    //the User display portion of the frame, one for white player and one for black player
    private JPanel white;
    private JPanel black;

    //the chessboard part of the application where you will be interacting with the pieces
    private ChessBoard chessBoard;

    //retain reference to the game that is being played on the GUI
    private ChessGame currentGame;

    private String firstSelect;
    private String secondSelect;


    public VisualConsole() {
        //Initialize overall frame and game to be displayed
        JFrame frame = new JFrame("Chess");
        currentGame = new ChessGame("John", "Michelle");
        JPanel container = new JPanel();
        JPanel playerContainer = new JPanel();

        //Initialize two subgroups and how they will be laid out
        JPanel boardAndPlayers = new JPanel();
        JPanel saveStateManager = new JPanel();



        //add chess board and players to boardandplayers
        //boardAndPlayers.setLayout(new BoxLayout(boardAndPlayers, BoxLayout.Y_AXIS));
        chessBoard = new ChessBoard(currentGame, this);
        boardAndPlayers.add(chessBoard);

        createPlayerDisplays(playerContainer, boardAndPlayers);
        createSaveStateButtons(saveStateManager);

        //Add components to a box layout panel and then add to frame
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(boardAndPlayers);
        container.add(saveStateManager);
        frame.add(container);


        //SET SIZE AND POSITION
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocation(CENTRE_SCREEN);

        //UPDATE LOGO IN TOP LEFT
        setLogo(frame);

        //BASIC REQUIREMENTS
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: //Initialize save and load and new buttons and add them to saveStateManager
    private void createSaveStateButtons(JPanel saveStateManager) {
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton neww = new JButton("New");
        save.addActionListener(this);
        save.setActionCommand("Save");
        load.addActionListener(this);
        load.setActionCommand("Load");
        neww.addActionListener(this);
        neww.setActionCommand("New");
        saveStateManager.add(save);
        saveStateManager.add(load);
        saveStateManager.add(neww);
    }

    //EFFECTS: //Create the white and black playerDisplay and add them to board and players
    private void createPlayerDisplays(JPanel playerContainer, JPanel boardAndPlayers) {
        white = new PlayerDisplay(currentGame, true);
        black = new PlayerDisplay(currentGame, false);

        playerContainer.setLayout(new BoxLayout(playerContainer, BoxLayout.Y_AXIS));
        playerContainer.add(white);
        playerContainer.add(black);
        playerContainer.setVisible(true);
        boardAndPlayers.add(playerContainer);
    }

    private void setLogo(JFrame frame) {
        ImageIcon icon = new ImageIcon("./data/UndefinedEngineBlackFront.jpg");

        BufferedImage bi = toBufferedImage(icon);

        frame.setIconImage(bi);
    }

    //code to convert IconImage to Buffered image found from user Werner Kvalem Vesteras on stackoverflow
    private BufferedImage toBufferedImage(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.dispose();
        return bi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            saveGame();
            System.out.println("Saved game");
        } else if (e.getActionCommand().equals("Load")) {
            loadGame();
            System.out.println("New game loaded");
        } else if (e.getActionCommand().equals("New")) {
            currentGame = new ChessGame("White", "Black");
            System.out.println("Created a new game");
        } else if (e.getActionCommand().equals("Select")) {
            System.out.println("CHESS SQUARE CLICKED");
            handleSelects(e);
            chessBoard.visualizeBoard();
        }
    }

    //EFFECTS: handles selects on the chessboard, moving/capturing pieces if needed
    private void handleSelects(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        int x = jb.getLocation().x;
        int y = jb.getLocation().y;
        Translator t = new Translator();
        String selectSquare = t.translateToChessCoord(x, y);
        if (firstSelect == null) {
            firstSelect = selectSquare;
        } else {
            int xx = t.translateToXCoord(firstSelect);
            int yy = t.translateToYCoord(firstSelect);
            secondSelect = selectSquare;
            if (!(currentGame.returnPieceOn(x, y) == null) && !(currentGame.returnPieceOn(xx, yy) == null)) {
                currentGame.returnPieceOn(xx, yy).captures(currentGame.getActive(), x, y);
            } else if (!(currentGame.returnPieceOn(xx, yy) == null)) {
                currentGame.returnPieceOn(xx, yy).move(currentGame.getActive(), x, y);
            } else {
                //do nothing
            }
            firstSelect = null;
            secondSelect = null;
        }
    }

    //REQUIRES: there is a folder in DEFAULT_FILE fitting requirements of JsonReader class
    //EFFECTS: loads the last game to be played on this computer
    private void loadGame() {
        JsonReader reader = new JsonReader(DEFAULT_FILE);
        try {
            currentGame = reader.read();
        } catch (IOException ioe) {
            // do nothing for now
        }
    }

    //EFFECTS: saves the current game to DEFAULT_FILE
    private void saveGame() {
        JsonWriter writer = new JsonWriter(DEFAULT_FILE);
        try {
            writer.open();
            writer.write(currentGame);
            writer.close();
        } catch (IOException ioe) {
            // do nothing for now
        }
    }

    public static void main(String[] args) {
        new VisualConsole();
    }
}

/*super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Change");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        label = new JLabel("flag");
        field = new JTextField(5);
        add(field);
        add(btn);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        */