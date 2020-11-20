package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// User is a class representing a player at a chess board, with information about what pieces they own and which
// enemy pieces they have captured, as well as what their name is. Can return any of this information and display it
// to console. !!!NOTE ANY CONSOLE INTERACTION NEEDS TO BE IN THE UI CLASS SO GET THOSE OUT OF HERE, TO FIX THIS JUST
// MAKE THE VOID RETURNS INSTEAD RETURN A STRING AND THEN HAVE THAT STRING PRINTED IN UI PART OF PROJECT!!!
public class User {
    private final String name;
    private ArrayList<ChessPiece> owned;
    private ArrayList<ChessPiece> captured;
    private ArrayList<String> threatened;
    private ArrayList<String> possibleMoves;
    //If boolean is true this owner is playing with the white pieces, if false they play with black pieces
    private boolean playingWhite;

    public User(boolean isWhite, String name) {
        this.name = name;
        owned = new ArrayList<ChessPiece>();
        captured = new ArrayList<ChessPiece>();
        playingWhite = isWhite;

        threatened = new ArrayList<String>();
        possibleMoves = new ArrayList<String>();
    }

    //taken and adjusted from JsonSerializationDemo
    //EFFECTS: returns this player as a JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Name", name);
        jsonObject.put("Is Playing White", playingWhite);
        jsonObject.put("Owned Active Pieces", ownedToJson());
        jsonObject.put("Captured Enemy Pieces", capturedToJson());

        return jsonObject;
    }

    //taken and adjusted from JsonSerializationDemo
    //EFFECTS: returns owned pieces in this game as JSON array
    public JSONArray ownedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ChessPiece p : owned) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    //taken and adjusted from JsonSerializationDemo
    //EFFECTS: returns owned pieces in this game as JSON array
    public JSONArray capturedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ChessPiece p : captured) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    /*//EFFECTS: Returns a list of all of the squares that this player is currently threatening to capture on
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
    }*/

    public ArrayList<ChessPiece> getCaptured() {
        return captured;
    }

    public ArrayList<ChessPiece> getOwned() {
        return owned;
    }

    public boolean isPlayingWhite() {
        return playingWhite;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getThreatened() {
        return threatened;
    }

    public ArrayList<String> getPossibleMoves() {
        return possibleMoves;
    }

}
