package model;

import exceptions.BadSelectException;
import exceptions.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChessGame {
    private ArrayList<ChessPiece> activePieces;
    private ArrayList<User> players;

    private User whitePlayer;
    private User blackPlayer;

    private boolean isWhiteTurn;
    private String enPassent;

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

    //Default constructor
    //EFFECTS: sets up ChessGame with standard starting pieces and locations
    public ChessGame(String whiteName, String blackName) {
        whitePlayer = new User(true, whiteName);
        blackPlayer = new User(false, blackName);
        players = new ArrayList<>();
        activePieces = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);

        isWhiteTurn = true;
        enPassent = "-";

        initializePawns();
        initializeOthers();
        givePiecesToWhite();
        givePiecesToBlack();
        setActiveList();
    }

    //Alternate constructor !!!
    //EFFECTS: sets up ChessGame with given players, enPassentSquare and Turn value
    public ChessGame(User whitePlayer, User blackPlayer, String enPassent, Boolean isWhiteTurn) {
        this.enPassent = enPassent;
        this.isWhiteTurn = isWhiteTurn;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        players = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);

        activePieces = new ArrayList<>();
        setActiveList();
    }

    //EFFECTS: updates the active list to be the owned pieces of black and white
    private void setActiveList() {
        activePieces.clear();
        activePieces.addAll(blackPlayer.getOwned());
        activePieces.addAll(whitePlayer.getOwned());
    }

    //EFFECTS: returns the piece on given x y coords if it belongs to the player who's turn it currently is, or null
    public ChessPiece returnFriendlyPieceOn(int pieceX, int pieceY) {
        ChessPiece selected = null;
        for (ChessPiece p : activePieces) {
            if (p.getPosX() == pieceX && p.getPosY() == pieceY && p.getOwner().isPlayingWhite() == isWhiteTurn) {
                selected = p;
            }
        }
        return selected;
    }

    //MODIFIES: piece p in activePieces on location
    //EFFECTS: moves the piece on location to target
    //         throw IllegalMoveException for bad movement, throw BadSelectException for bad select
    public void performMove(String location, String target) throws IllegalMovementException, BadSelectException {
        Translator translator = new Translator();
        int pieceX = translator.translateToXCoord(location);
        int pieceY = translator.translateToYCoord(location);
        int targetX = translator.translateToXCoord(target);
        int targetY = translator.translateToYCoord(target);

        ChessPiece p = returnFriendlyPieceOn(pieceX, pieceY);

        if (returnFriendlyPieceOn(pieceX, pieceY) == null) {
            throw new BadSelectException();
        } else if (p.move(activePieces, targetX, targetY)) {
            isWhiteTurn = !isWhiteTurn;
        } else {
            throw new IllegalMovementException();
        }
    }

    //MODIFIES: pieces in activePieces on location and target
    //EFFECTS: capture the piece on target with the piece on location, or print why it's impossible
    public void performCapture(String location, String target) throws IllegalCaptureException, BadSelectException {
        Translator translator = new Translator();
        int pieceX = translator.translateToXCoord(location);
        int pieceY = translator.translateToYCoord(location);
        int targetX = translator.translateToXCoord(target);
        int targetY = translator.translateToYCoord(target);

        ChessPiece p = returnFriendlyPieceOn(pieceX, pieceY);

        if (returnFriendlyPieceOn(pieceX, pieceY) == null) {
            throw new BadSelectException();
        } else if (p.captures(activePieces, targetX, targetY)) {
            isWhiteTurn = !isWhiteTurn;
        } else {
            throw new IllegalCaptureException();
        }
    }

    //taken and adjusted from JsonSerializationDemo
    //EFFECTS: returns users and pieces in this game as JSON array
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Is White's Turn", isWhiteTurn);
        json.put("En Passent Square", enPassent);
        json.put("White Player", whitePlayer.toJson());
        json.put("Black Player", blackPlayer.toJson());
        return json;
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

    //REQUIRES: owners of pieces in pieces have same name as either whiteplayer or blackplayer
    //MODIFIES: this (white and black players owned pieces)
    //EFFECTS: gives each piece in pieces to either whiteplayer or blackplayer
    private void giveCustomPiecesToOwners(ArrayList<ChessPiece> pieces) {
        for (ChessPiece p : pieces) {
            if (p.getOwner().getName() == this.getWhitePlayer().getName()) {
                this.getWhitePlayer().getOwned().add(p);
            } else if (p.getOwner().getName() == this.getBlackPlayer().getName()) {
                this.getBlackPlayer().getOwned().add(p);
            }
        }
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

    //BASIC GETTERS AND SETTERS

    //EFFECTS: returns active pieces
    public ArrayList<ChessPiece> getActive() {
        return activePieces;
    }

    //EFFECTS: returns White Player
    public User getWhitePlayer() {
        return whitePlayer;
    }

    //EFFECTS: returns Black Player
    public User getBlackPlayer() {
        return blackPlayer;
    }

    //EFFECTS: returns whether or not it is Whites turn currently
    public Boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }

    //EFFECTS: sets isWhiteTurn to bool
    public void setIsWhiteTurn(boolean bool) {
        isWhiteTurn = bool;
    }

    //EFFECTS: sets enPassent to square
    public void setEnPassent(String square) {
        enPassent = square;
    }

    //EFFECTS: gets enPassent
    public String getEnPassent() {
        return enPassent;
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
