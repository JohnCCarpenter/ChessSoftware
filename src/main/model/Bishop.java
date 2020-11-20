package model;

import java.util.ArrayList;

import static java.lang.Math.abs;

// Extends the ChessPiece class to include specific information about bishops. Bishops may only move along diagonals
// (move equal amounts in x and y) and CANNOT move through pieces. To capture, bishops simply need to move to an
// occupied square and they capture the piece on the square.
public class Bishop extends ChessPiece {

    public Bishop(int x, int y, User owner) {
        super(x, y, owner);
        value = 3;
    }

    public Bishop(int x, int y, User owner, boolean isCaptured, boolean hasMoved) {
        super(x, y, owner, isCaptured, hasMoved);
        value = 3;
    }

    @Override
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        if (abs(x - this.getPosX()) != abs(y - this.getPosY())) {
            return false;
        } else if (x == this.getPosX() && y == this.getPosY()) {
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
        if (abs(x - this.getPosX()) != abs(y - this.getPosY())) {
            return false;
        } else if (checkPath(active, x, y)) {
            return false;
        } else if (!(checkIsOccupied(active, x, y))) {
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
        int dirX = (x - this.getPosX()) / abs(x - this.getPosX());
        int dirY = (y - this.getPosY()) / abs(y - this.getPosY());
        //stopX length == stopsY length and each position corresponds to each other
        ArrayList<Integer> stopsX = new ArrayList<>();
        ArrayList<Integer> stopsY = new ArrayList<>();
        genStops(dirX, dirY, stopsX, stopsY, x, y);
        return checkBlocked(active, stopsX, stopsY);
    }


}
