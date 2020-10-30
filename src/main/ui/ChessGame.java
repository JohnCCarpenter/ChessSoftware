package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

//ChessGame sets up and contains information about a standard game of chess that is being played between 2 players
public class ChessGame {
    private Scanner input;

    private ArrayList<ChessPiece> activePieces;
    private ArrayList<User> players;

    private User whitePlayer;
    private User blackPlayer;

    boolean whiteTurn = true;

    //White pawns
    private ChessPiece wp1;
    private ChessPiece wp2;
    private ChessPiece wp3;
    private ChessPiece wp4;
    private ChessPiece wp5;
    private ChessPiece wp6;
    private ChessPiece wp7;
    private ChessPiece wp8;
    //White other pieces
    private ChessPiece wr1;
    private ChessPiece wr2;
    private ChessPiece wn1;
    private ChessPiece wn2;
    private ChessPiece wb1;
    private ChessPiece wb2;
    private ChessPiece wq;
    private ChessPiece wk;
    //Black pawns
    private ChessPiece bp1;
    private ChessPiece bp2;
    private ChessPiece bp3;
    private ChessPiece bp4;
    private ChessPiece bp5;
    private ChessPiece bp6;
    private ChessPiece bp7;
    private ChessPiece bp8;
    //Black other pieces
    private ChessPiece br1;
    private ChessPiece br2;
    private ChessPiece bn1;
    private ChessPiece bn2;
    private ChessPiece bb1;
    private ChessPiece bb2;
    private ChessPiece bq;
    private ChessPiece bk;

    //EFFECTS: runs the Chess Game
    public ChessGame() {
        runChessGame();
    }

    //MODIFIES: this, activePieces, players, whiteTurn, and all ChessPiece fields
    //EFFECTS: processes commands of two players playing the game of chess
    private void runChessGame() {
        boolean gameOver = false;
        String location;
        String action;
        String target;

        setup();

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
            } else {
                processMove(location, action, target);
            }
        }

    }

    //REQUIRES: Called with action as one of "move" or "capture"
    //EFFECTS: performs the requested move
    private void processMove(String location, String action, String target) {
        if (action.equals("move")) {
            performMove(location, target);
        } else if (action.equals("capture")) {
            performCapture(location, target);
        } else if (action.equals("list")) {
            printCaptures();
        }
    }

    //EFFECTS: prints out the list of captures of the player who's turn it currently is
    private void printCaptures() {
        if (whiteTurn) {
            System.out.print(whitePlayer.getName() + " has captured ");
            for (ChessPiece p : whitePlayer.getCaptured()) {
                System.out.print(p.printSymbol());
                System.out.print(" ");
            }
        } else {
            System.out.print(blackPlayer.getName() + " has captured ");
            for (ChessPiece p : blackPlayer.getCaptured()) {
                System.out.print(p.printSymbol());
                System.out.print(" ");
            }
        }
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
                for (ChessPiece p : activePieces) {
                    if (p.getPosX() == y && p.getPosY() == x) {
                        System.out.print(p.printSymbol());
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

    //MODIFIES: piece p in activePieces on location
    //EFFECTS: moves the piece on location to target, or print why it's impossible
    private void performMove(String location, String target) {
        Translator translator = new Translator();
        int pieceX = translator.translateToXCoord(location);
        int pieceY = translator.translateToYCoord(location);
        int targetX = translator.translateToXCoord(target);
        int targetY = translator.translateToYCoord(target);
        boolean goodSelect = false;

        for (ChessPiece p : activePieces) {
            if (p.getPosX() == pieceX && p.getPosY() == pieceY && p.getOwner().isPlayingWhite() == whiteTurn) {
                if (p.move(activePieces, targetX, targetY)) {
                    System.out.println("Moved piece on " + location + " to " + target);
                    whiteTurn = !whiteTurn;
                } else {
                    System.out.println("Illegal movement, try again");
                }
                goodSelect = true;
            }
        }
        if (!goodSelect) {
            System.out.println("No friendly pieces on " + location + ", try again");
        }
    }

    //MODIFIES: pieces in activePieces on location and target
    //EFFECTS: capture the piece on target with the piece on location, or print why it's impossible
    private void performCapture(String location, String target) {
        Translator translator = new Translator();
        int pieceX = translator.translateToXCoord(location);
        int pieceY = translator.translateToYCoord(location);
        int targetX = translator.translateToXCoord(target);
        int targetY = translator.translateToYCoord(target);

        ChessPiece p = returnPieceOn(activePieces, pieceX, pieceY, whiteTurn);

        if (returnPieceOn(activePieces, pieceX, pieceY, whiteTurn) == null) {
            System.out.println("No friendly pieces on " + location + ", try again");
        } else if (p.captures(activePieces, targetX, targetY)) {
            System.out.println("Captured piece on " + target + " with piece on " + location);
            whiteTurn = !whiteTurn;
        } else {
            System.out.println("Illegal capture, try again");
        }
    }

    public ChessPiece returnPieceOn(ArrayList<ChessPiece> activePieces, int pieceX, int pieceY, boolean whiteTurn) {
        ChessPiece selected = null;
        for (ChessPiece p : activePieces) {
            if (p.getPosX() == pieceX && p.getPosY() == pieceY && p.getOwner().isPlayingWhite() == whiteTurn) {
                selected = p;
            }
        }
        return selected;
    }

    //EFFECTS: sets up the pieces in the correct positions for a standard chess game, asks console for player names!
    private void setup() {
        input = new Scanner(System.in);
        System.out.println("Who is playing with the white pieces?");
        String whiteName = input.next();
        System.out.println("Who is playing with the black pieces?");
        String blackName = input.next();
        whitePlayer = new User(true, whiteName);
        blackPlayer = new User(false, blackName);
        players = new ArrayList<>();
        activePieces = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);
        initializePawns();
        initializeOthers();
        givePiecesToWhite();
        givePiecesToBlack();
        setActiveList();
        System.out.println("Game set up for " + whiteName + " as white and " + blackName + " as black");

    }

    //EFFECTS: sets up the pawns in the correct position for a standard chess game
    private void initializePawns() {
        wp1 = new Pawn(0, 1, whitePlayer);
        wp2 = new Pawn(1, 1, whitePlayer);
        wp3 = new Pawn(2, 1, whitePlayer);
        wp4 = new Pawn(3, 1, whitePlayer);
        wp5 = new Pawn(4, 1, whitePlayer);
        wp6 = new Pawn(5, 1, whitePlayer);
        wp7 = new Pawn(6, 1, whitePlayer);
        wp8 = new Pawn(7, 1, whitePlayer);
        bp1 = new Pawn(0, 6, blackPlayer);
        bp2 = new Pawn(1, 6, blackPlayer);
        bp3 = new Pawn(2, 6, blackPlayer);
        bp4 = new Pawn(3, 6, blackPlayer);
        bp5 = new Pawn(4, 6, blackPlayer);
        bp6 = new Pawn(5, 6, blackPlayer);
        bp7 = new Pawn(6, 6, blackPlayer);
        bp8 = new Pawn(7, 6, blackPlayer);
    }

    //EFFECTS: sets up all non-pawn pieces in the correct positions for a standard chess game
    private void initializeOthers() {
        wr1 = new Rook(0, 0, whitePlayer);
        wn1 = new Knight(1, 0, whitePlayer);
        wb1 = new Bishop(2, 0, whitePlayer);
        wq = new Queen(3, 0, whitePlayer);
        wk = new King(4, 0, whitePlayer);
        wb2 = new Bishop(5, 0, whitePlayer);
        wn2 = new Knight(6, 0, whitePlayer);
        wr2 = new Rook(7, 0, whitePlayer);
        br1 = new Rook(0, 7, blackPlayer);
        bn1 = new Knight(1, 7, blackPlayer);
        bb1 = new Bishop(2, 7, blackPlayer);
        bq = new Queen(3, 7, blackPlayer);
        bk = new King(4, 7, blackPlayer);
        bb2 = new Bishop(5, 7, blackPlayer);
        bn2 = new Knight(6, 7, blackPlayer);
        br2 = new Rook(7, 7, blackPlayer);
    }

    //EFFECTS: updates the active list to be the owned pieces of black and white
    private void setActiveList() {
        activePieces.clear();
        activePieces.addAll(blackPlayer.getOwned());
        activePieces.addAll(whitePlayer.getOwned());
    }

    //EFFECTS: gives the black user all the black pieces
    private void givePiecesToBlack() {
        blackPlayer.getOwned().add(bp1);
        blackPlayer.getOwned().add(bp2);
        blackPlayer.getOwned().add(bp3);
        blackPlayer.getOwned().add(bp4);
        blackPlayer.getOwned().add(bp5);
        blackPlayer.getOwned().add(bp6);
        blackPlayer.getOwned().add(bp7);
        blackPlayer.getOwned().add(bp8);
        blackPlayer.getOwned().add(br1);
        blackPlayer.getOwned().add(br2);
        blackPlayer.getOwned().add(bn1);
        blackPlayer.getOwned().add(bn2);
        blackPlayer.getOwned().add(bb1);
        blackPlayer.getOwned().add(bb2);
        blackPlayer.getOwned().add(bq);
        blackPlayer.getOwned().add(bk);
    }

    //EFFECTS: gives the white user all the white pieces
    private void givePiecesToWhite() {
        whitePlayer.getOwned().add(wp1);
        whitePlayer.getOwned().add(wp2);
        whitePlayer.getOwned().add(wp3);
        whitePlayer.getOwned().add(wp4);
        whitePlayer.getOwned().add(wp5);
        whitePlayer.getOwned().add(wp6);
        whitePlayer.getOwned().add(wp7);
        whitePlayer.getOwned().add(wp8);
        whitePlayer.getOwned().add(wr1);
        whitePlayer.getOwned().add(wr2);
        whitePlayer.getOwned().add(wn1);
        whitePlayer.getOwned().add(wn2);
        whitePlayer.getOwned().add(wb1);
        whitePlayer.getOwned().add(wb2);
        whitePlayer.getOwned().add(wq);
        whitePlayer.getOwned().add(wk);
    }


    /*//EFFECTS: returns whether one of the Kings is currently in check
    public boolean check() {
        return false;   //stub
    }

    //EFFECTS: returns whether one of the Kings is in checkmate
    public boolean checkMate() {
        return false;   //stub
    }

    //EFFECTS: returns whether there is a piece at position (x, y)
    public boolean testOccupied() {
        return false;   //stub
    }*/

}

