package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

// User is a class representing a player at a chess board, with information about what pieces they own and which
// enemy pieces they have captured, as well as what their name is. Can return any of this information and display it
// to console.
public class User implements Writable {
    private final String name;
    private ChessGame currentGame;
    private ArrayList<ChessPiece> owned;
    private ArrayList<ChessPiece> captured;
    //If boolean is true this owner is playing with the white pieces, if false they play with black pieces
    private boolean playingWhite;
    private boolean isInCheck;

    private ArrayList<String> threatened;
    private ArrayList<String> possibleMoves;


    public User(boolean isWhite, String name) {
        this.name = name;
        owned = new ArrayList<ChessPiece>();
        captured = new ArrayList<ChessPiece>();
        playingWhite = isWhite;
        currentGame = null;

        threatened = new ArrayList<String>();
        possibleMoves = new ArrayList<String>();
    }

    public User(boolean isWhite, String name, ChessGame currentGame) {
        this.name = name;
        owned = new ArrayList<ChessPiece>();
        captured = new ArrayList<ChessPiece>();
        playingWhite = isWhite;
        this.currentGame = currentGame;

        threatened = new ArrayList<String>();
        possibleMoves = new ArrayList<String>();
    }

//    //EFFECTS: returns all of the squares that this player threatens
//    public HashSet<String> generateThreats() {
//        HashSet<String> threats = new HashSet<String>();
//
//        for (ChessPiece p: owned) {
//            threats.addAll(p.generateThreats(getCurrentGame().getActive()));
//        }
//
//        return threats;
//    }
//
//    //REQUIRES: this player has a king
//    //EFFECTS: returns the position of the king of this player
//    public String getKingPosition() {
//        Translator t = new Translator();
//        for (ChessPiece p: owned) {
//            if (p.symbol().toLowerCase().equals("k")) {
//                return t.translateToChessCoord(p.getPosX(), p.getPosY());
//            }
//        }
//        //if no king found
//        return null;
//    }

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

    //EFFECTS: Returns a list of all of the squares that this player is currently threatening to capture on
    public ArrayList<String> threaten() {
        return null;
    }

//    //EFFECTS: Returns a list of all of the possible moves for this user
//    public ArrayList<Move> generateMoves() {
//
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return isPlayingWhite() == user.isPlayingWhite()
                && getName().equals(user.getName())
                && getOwned().equals(user.getOwned())
                && getCaptured().equals(user.getCaptured());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getOwned(), getCaptured(), isPlayingWhite());
    }

    //GETTERS AND SETTERS
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

    public ChessGame getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(ChessGame currentGame) {
        this.currentGame = currentGame;
    }

    public boolean isInCheck() {
        return isInCheck;
    }

    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
    }
}
