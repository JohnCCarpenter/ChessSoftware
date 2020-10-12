package model;

import java.util.ArrayList;

// User is a class representing a player at a chess board, with information about what pieces they own and which
// enemy pieces they have captured, as well as what their name is. Can return any of this information and display it
// to console.
public class User {
    private ArrayList<ChessPiece> owned;
    private ArrayList<ChessPiece> captured;
    private ArrayList<String> threatened;
    private ArrayList<String> possibleMoves;

    public User() {
        owned = null;
        captured = null;
        threatened = null;
        possibleMoves = null;
    }

    //EFFECTS: Returns a list of all of the squares that this player is currently threatening to capture on
    public ArrayList<String> threaten() {
        return null;
    }

    //EFFECTS: prints to the console the list of ChessPieces that have been captured by this user
    public void displayCaptures() {
        //stub
    }

    //EFFECTS: prints to the console the list of active ChessPieces that this user owns, as well as their locations
    public void displayOwned() {
        //stub
    }

    //EFFECTS: prints to the console the list of possible moves that this user can make
    public void displayOptions() {
        //stub
    }

    public ArrayList<ChessPiece> getCaptured() {
        return captured;
    }

    public ArrayList<ChessPiece> getOwned() {
        return owned;
    }

    public ArrayList<String> getThreatened() {
        return threatened;
    }

    public ArrayList<String> getPossibleMoves() {
        return possibleMoves;
    }

}
