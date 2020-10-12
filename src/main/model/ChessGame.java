package model;

import java.util.ArrayList;

//ChessGame sets up and contains information about a standard game of chess that is being played between 2 players
public class ChessGame {
    private ArrayList<ChessPiece> activePieces;
    private ArrayList<User> players;
    private ArrayList<String> moves;

    public static final int BOARD_SIZE = 8;

    //EFFECTS: sets up the pieces in the correct positions for a standard chess game, asks console for player names!
    public void setup() {}

    //EFFECTS: sets up the pawns in the correct position for a standard chess game
    private void setupPawns() {}

    //EFFECTS: sets up all non-pawn pieces in the correct positions for a standard chess game
    private void setupOthers() {}

    //EFFECTS: returns whether one of the Kings is currently in check
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
    }

}




    /*//EFFECTS: checks if there is a piece at location (x, y) in the chessboard matrix
    public boolean isOccupied(int x, int y) {
        return false;   //stub
    }

    //REQUIRES: position x, y in chessboard is null
    //MODIFIES: this and cp
    //EFFECTS: adds cp to position (x,y) in chessboard matrix, updates position of cp to reflect this
    public void add(ChessPiece cp, int x, int y) {

    }

    //REQUIRES: position x, y in chessboard is occupied by a ChessPiece
    //MODIFIES: this and ChessPiece
    //EFFECTS: removes the ChessPiece on position (x,y) in chessboard matrix,
    public void remove(int x, int y) {

    }*/

