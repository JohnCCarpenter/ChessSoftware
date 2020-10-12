package model;

import java.util.ArrayList;

// ChessPiece abstract class represents a generic chess piece occupying a certain position on a chessboard
// it also has the ability to move from one location to another and capture from it's current location at another
// location. The legality of the moves and captures MUST be implemented in the subclasses representing the 6 distinct
// types of chess pieces. The help function prints the specifications of this legality to the console so that the user
// may learn how to play the game.
public abstract class ChessPiece {

    protected boolean isCaptured;
    protected boolean hasMoved;
    protected int posX;
    protected int posY;
    protected int value;
    protected User owner;


    //REQUIRES: The space (x, y) is not currently occupied by another ChessPiece
    //EFFECTS: Constructs a generic ChessPiece object at coordinates (x, y) on a chess board, owned by owner
    public ChessPiece(int x, int y, User owner) {
        posX = x;
        posY = y;
        this.owner = owner;
        isCaptured = false;
        hasMoved = false;
    }

    //MODIFIES: this
    //EFFECTS: Changes pieces position to coordinates (x, y) on a chess board
    //         returns true if the move is legal to play in the game according to active pieces passed in
    public boolean move(ArrayList<ChessPiece> active, int x, int y) {
        return false;   //stub
    }


    //MODIFIES: this, active
    //EFFECTS: Captures the piece at coordinates (x, y) in the list of active pieces for a chess game
    //          returns false if there is no legal capture at pos (including movement to get to location) according
    //          to active pieces passed in
    public boolean captures(ArrayList<ChessPiece> active, int x, int y) {
        return false;   //stub
    }

    //EFFECTS: returns true if moving x right and y up is a legal move in chess game with active pieces passed in
    public abstract boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y);

    //EFFECTS: returns true if capturing on the square x right and y up is a legal move in chess game with active pieces
    //         passed in
    public abstract Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y);

    //Below here are getters and setters for the ChessPiece set of classes
    public void setCaptured(boolean bool) {
        this.isCaptured = bool;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean getCaptured() {
        return isCaptured;
    }

    public int getValue() {
        return value;
    }
}
