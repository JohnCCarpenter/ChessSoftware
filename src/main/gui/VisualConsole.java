package gui;

import model.ChessGame;

import javax.swing.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

//Displays the entirety of the chess game GUI
public class VisualConsole extends JFrame implements ActionListener {
    private static final Point CENTRE_SCREEN = new Point(400, 150);
    public static final Dimension BOARD_SIZE = new Dimension(400, 400);

    //the User display portion of the frame, one for white player and one for black player
    private JPanel white;
    private JPanel black;

    //the chessboard part of the application where you will be interacting with the pieces
    private ChessBoard chessBoard;

    //retain reference to the game that is being played on the GUI
    private ChessGame currentGame;




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
        chessBoard = new ChessBoard(currentGame);
        boardAndPlayers.add(chessBoard);

        //Create the white and black playerDisplay and add them to board and players
        white = new PlayerDisplay(currentGame, true);
        black = new PlayerDisplay(currentGame, false);

        //NOTE THAT THESE NEED TO BE ADDED TO THEIR OWN SUBGROUP SO THEY STACK ABOVE ONE ANOTHER
        playerContainer.setLayout(new BoxLayout(playerContainer, BoxLayout.Y_AXIS));
        playerContainer.add(white);
        playerContainer.add(black);
        playerContainer.setVisible(true);
        boardAndPlayers.add(playerContainer);



        //Initialize save and load buttons and add them to saveStateManager
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        saveStateManager.add(save);
        saveStateManager.add(load);

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

    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("myButton")) {
//            label.setText(field.getText());
//        }
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