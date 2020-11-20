package ui;

import model.*;
import exceptions.*;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//ChessGame sets up and contains information about a standard game of chess that is being played between 2 players
public class TextConsole {
    public static final String DEFAULT_FILE = "./data/lastSave.json";

    private Scanner input;
    private ChessGame thisGame;
    private String location;
    private String action;
    private String target;
    private String whiteName;
    private String blackName;

    //EFFECTS: runs the Chess Game
    public TextConsole() {
        input = new Scanner(System.in);

        System.out.println("Who is playing with the white pieces?");
        whiteName = input.next();
        System.out.println("Who is playing with the black pieces?");
        blackName = input.next();

        thisGame = new ChessGame(whiteName, blackName);
        runChessGame();
    }

    //Alternate constructor to make a ChessGame with given pieces
    //EFFECTS: runs a custom Chess Game with the active pieces given at start
    public TextConsole(ChessGame cg) {
        input = new Scanner(System.in);
        thisGame = cg;
        runChessGame();
    }

    //MODIFIES: this, activePieces, players, whiteTurn, and all ChessPiece fields
    //EFFECTS: processes commands of two players playing the game of chess
    private void runChessGame() {
        boolean gameOver = false;

        while (!gameOver) {
            visualizeGame();
            System.out.println("Where is the piece you would like to move?");
            location = input.next();
            System.out.println("Capture, Move, List, or Quit?");
            action = input.next();
            System.out.println("What is the target location?");
            target = input.next();
            location = location.toLowerCase();
            action = action.toLowerCase();
            target = target.toLowerCase();

            if (action.equals("quit")) {
                gameOver = true;
                saveGameQuery();
            } else {
                processMove(location, action, target);
            }
        }

    }

    //EFFECTS: Handles questioning on if players want to save the game
    private void saveGameQuery() {
        System.out.println("Do you want to save this game?");
        String save = input.next();
        save.toLowerCase();
        if (save.equals("yes")) {
            try {
                saveGame();
                System.out.println("Saved game successfully");
            } catch (FileNotFoundException fnfe) {
                System.out.println("File not found");
                saveGameQuery();
            } catch (IOException ioe) {
                System.out.println("IO exception thrown");
                saveGameQuery();
            }
        }
    }

    //EFFECTS: saves game to DEFAULT_FILE
    private void saveGame() throws IOException, FileNotFoundException {
        JsonWriter writer = new JsonWriter(DEFAULT_FILE);
        try {
            writer.open();
            writer.write(thisGame);
            System.out.println("Saved game to " + DEFAULT_FILE);
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Unable to save game to: " + DEFAULT_FILE);
        }
    }

    //REQUIRES: Called with action as one of "move" or "capture" or "list"
    //EFFECTS: performs the requested move
    private void processMove(String location, String action, String target) {
        if (action.equals("move")) {
            try {
                thisGame.performMove(location, target);
                System.out.println("Moved piece on " + location + " to " + target);
            } catch (IllegalMovementException ime) {
                System.out.println("Illegal movement, try again");
            } catch (BadSelectException bse) {
                System.out.println("No friendly pieces on " + location + ", try again");
            }
        } else if (action.equals("capture")) {
            try {
                thisGame.performCapture(location, target);
                System.out.println("Captured piece on " + target + " with piece on " + location);
            } catch (IllegalCaptureException ime) {
                System.out.println("Illegal movement, try again");
            } catch (BadSelectException bse) {
                System.out.println("No friendly pieces on " + location + ", try again");
            }
        } else if (action.equals("list")) {
            printCaptures();
        }
    }

    //EFFECTS: prints out the list of captures of the player who's turn it currently is
    private void printCaptures() {
        if (thisGame.getIsWhiteTurn()) {
            System.out.print(thisGame.getWhitePlayer().getName() + " has captured ");
            for (ChessPiece p : thisGame.getWhitePlayer().getCaptured()) {
                System.out.print(p.symbol());
                System.out.print(" ");
            }
        } else {
            System.out.print(thisGame.getBlackPlayer().getName() + " has captured ");
            for (ChessPiece p : thisGame.getBlackPlayer().getCaptured()) {
                System.out.print(p.symbol());
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    //REQUIRES: No pieces in activePieces share a space
    //EFFECTS: prints a visualization of the current chessboard (represented by activePieces) to console
    private void visualizeGame() {
        int x;
        int y;
        for (x = 7; x >= 0; x--) {
            for (y = 0; y <= 7; y++) {
                boolean occupied = false;
                System.out.print(" ");
                for (ChessPiece p : thisGame.getActive()) {
                    if (p.getPosX() == y && p.getPosY() == x) {
                        System.out.print(p.symbol());
                        occupied = true;
                    }
                }
                if (!occupied) {
                    System.out.print('*');
                }
            }
            System.out.println();
        }
    }

}