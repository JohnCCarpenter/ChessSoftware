package model;

import java.util.ArrayList;

import static java.lang.Math.abs;

// Extends the ChessPiece class to include specific information about Rooks. Rooks move along straight lines
// (any number of squares in one of x or y) and CANNOT move through pieces.
// To capture, rooks simply need to move to an occupied square and they capture the piece on that square.
public class Rook extends ChessPiece {


    public Rook(int x, int y, User owner) {
        super(x, y, owner);
        value = 5;
    }

    @Override
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        if (((x - this.getPosX()) == 0) == ((y - this.getPosY()) == 0)) {
            return false;
        } else if (x < 0 || x > 7) {
            return false;
        } else if (y < 0 || y > 7) {
            return false;
        } else if (checkIsOccupied(active, x, y)) {
            return false;
        } else {
            return (!(checkPath(active, x, y)));
        }
    }

    @Override
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        if (((x - this.getPosX()) == 0) == ((y - this.getPosY()) == 0)) {
            return false;
        } else if (!(checkIsOccupied(active, x, y))) {
            return false;
        } else if (checkPath(active, x, y)) {
            return false;
        } else {
            ChessPiece occupier = null;
            for (ChessPiece p : active) {
                if (p.getPosX() == x && p.getPosY() == y) {
                    occupier = p;
                }
            }
            return (!(this.getOwner().isPlayingWhite() == occupier.getOwner().isPlayingWhite()));
        }
    }

    //REQUIRES: movement from this position to x, y is legal if active is empty other than this
    //EFFECTS: checks if there are any pieces on the path from this position to x, y according to Pieces movement
    private boolean checkPath(ArrayList<ChessPiece> active, int x, int y) {
        int dirX = 0;
        int dirY = 0;
        if (x == this.getPosX()) {
            dirY = (y - this.getPosY()) / abs(y - this.getPosY());
        }
        if (y == this.getPosY()) {
            dirX = (x - this.getPosX()) / abs(x - this.getPosX());
        }
        ArrayList<Integer> stopsX = new ArrayList<>();
        ArrayList<Integer> stopsY = new ArrayList<>();
        genStops(dirX, dirY, stopsX, stopsY, x, y);
        return checkBlocked(active, stopsX, stopsY);
    }
}
