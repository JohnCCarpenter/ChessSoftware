package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

    //REQUIRES: 0 <= x <= 7 & 0 <= y <= 7
    //EFFECTS: Constructs a generic ChessPiece object at coordinates (x, y) on a chess board, owned by owner
    public ChessPiece(int x, int y, User owner) {
        posX = x;
        posY = y;
        this.owner = owner;
        isCaptured = false;
        hasMoved = false;
    }

    //REQUIRES: 0 <= x <= 7 & 0 <= y <= 7
    //EFFECTS: Constructs a generic ChessPiece object at coordinates (x, y) on a chess board, owned by owner
    //          this constructor also contains information about whether the piece has moved or been captured
    public ChessPiece(int x, int y, User owner, boolean isCaptured, boolean hasMoved) {
        posX = x;
        posY = y;
        this.owner = owner;
        this.isCaptured = isCaptured;
        this.hasMoved = hasMoved;
    }

    //MODIFIES: this
    //EFFECTS: Changes pieces position to coordinates (x, y) on a chess board
    //         returns true if the move is legal to play in the game according to active pieces passed in
    public boolean move(ArrayList<ChessPiece> active, int x, int y) {
        if (this.isLegalMove(active, x, y)) {
            this.setPosX(x);
            this.setPosY(y);
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: piece being captured is legally situated in chessboard (x, y within bounds [0,7])
    //MODIFIES: this, active
    //EFFECTS: Captures the piece at coordinates (x, y) in the list of active pieces for a chess game
    //          returns false if there is no legal capture at pos (including movement to get to location) according
    //          to active pieces passed in
    public boolean captures(ArrayList<ChessPiece> active, int x, int y) {
        if (this.isLegalCapture(active, x, y)) {
            ChessPiece p = returnPieceOn(active, x, y);
            p.setCaptured(true);
            active.remove(p);
            p.getOwner().getOwned().remove(p);
            this.getOwner().getCaptured().add(p);
            this.setPosX(x);
            this.setPosY(y);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns the piece in activePieces at square pieceX, pieceY
    public ChessPiece returnPieceOn(ArrayList<ChessPiece> activePieces, int pieceX, int pieceY) {
        ChessPiece selected = null;
        for (ChessPiece p : activePieces) {
            if (p.getPosX() == pieceX && p.getPosY() == pieceY) {
                selected = p;
            }
        }
        return selected;
    }

    /*//REQUIRES: upgrade is one of "Rook", "Knight", "Bishop", "Queen"
    //MODIFIES: this and newPiece
    //EFFECTS: removes this piece from play and replaces it with a new piece of type upgrade called newPiece
    public void promote(String upgrade, ArrayList<ChessPiece> active) {
        if (upgrade == "Knight") {
            this.newPiece = new Knight(this.getPosX(), this.getPosY(), this.getOwner());
        } else if (upgrade == "Bishop") {
            this.newPiece = new Bishop(this.getPosX(), this.getPosY(), this.getOwner());
        } else if (upgrade == "Queen") {
            this.newPiece = new Queen(this.getPosX(), this.getPosY(), this.getOwner());
        } else if (upgrade == "Rook") {
            this.newPiece = new Rook(this.getPosX(), this.getPosY(), this.getOwner());
        } else {
            newPiece = null;
        }
        active.add(this.newPiece);
        active.remove(this);
    }*/

    //EFFECTS: returns true if moving to position (x, y) on board is a legal move in game with active pieces passed in
    public abstract boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y);

    //EFFECTS: returns true if capturing at position (x, y) on board is a legal move in chess game with active pieces
    //         passed in
    public abstract Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y);

    //EFFECTS: Returns true if square x, y is occupied by any piece
    protected boolean checkIsOccupied(ArrayList<ChessPiece> active, int x, int y) {
        boolean occupied = false;
        for (ChessPiece p : active) {
            if (!this.equals(p)) {
                if (p.getPosX() == x && p.getPosY() == y) {
                    occupied = true;
                }
            }
        }
        return occupied;
    }

    //REQUIRES: stopsX and stopsY are empty, moving to x, y is legal for this piece
    //MODIFIES: stopsX, stopsY
    //EFFECTS: creates a list of stops between this position and x, y for piece movement (not including x, y)
    protected void genStops(int dirX, int dirY, ArrayList<Integer> stopsX, ArrayList<Integer> stopsY, int x, int y) {
        int currentX = this.getPosX();
        int currentY = this.getPosY();
        while (!(currentX == x && currentY == y)) {
            stopsX.add(currentX);
            stopsY.add(currentY);
            currentX += dirX;
            currentY += dirY;
        }

    }

    //REQUIRES: len(stopsX) == len(stopsY) and each index relates to the same position in the other
    //EFFECTS: returns true if there is a piece in the path provided by stopsX and stopsY
    protected boolean checkBlocked(ArrayList<ChessPiece> active, ArrayList<Integer> stopsX, ArrayList<Integer> stopsY) {
        int yy = 0;
        boolean blocked = false;
        for (int xx : stopsX) {
            if (checkIsOccupied(active, xx, stopsY.get(yy))) {
                blocked = true;
            }
            yy += 1;
        }
        return blocked;
    }

    //EFFECTS: returns the symbol corresponding to this pieces class, white pieces are capital, black lowercase
    //         Rook = r, Knight = n, Bishop = b, Pawn = p, Queen = q, King = k
    public String printSymbol() {
        String symbol;
        if (this instanceof Bishop) {
            symbol = "B";
        } else if (this instanceof  Knight) {
            symbol = "N";
        } else if (this instanceof Rook) {
            symbol = "R";
        } else if (this instanceof Queen) {
            symbol = "Q";
        } else if (this instanceof King) {
            symbol = "K";
        } else {
            symbol = "P";
        }
        if (!(this.getOwner().isPlayingWhite())) {
            symbol = symbol.toLowerCase();
        }
        return symbol;
    }

    //taken and adjusted from JsonSerializationDemo
    //EFFECTS: returns chessPiece as a JSON object
    public JSONObject toJson() {
        Translator translator = new Translator();
        JSONObject json = new JSONObject();

        json.put("Piece Type", printSymbol().toUpperCase());
        json.put("Piece Location", translator.translateToChessCoord(posX, posY));
        json.put("Is Captured", isCaptured);
        json.put("Has Moved", hasMoved);

        return json;
    }

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

    public void setHasMoved(boolean b) {
        this.hasMoved = b;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean getIsCaptured() {
        return isCaptured;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public int getValue() {
        return value;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
