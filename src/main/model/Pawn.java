package model;

import java.util.ArrayList;

import static java.lang.Math.abs;

// Extends the ChessPiece class to include specific information about pawns. Pawns may only move one square forward
// except for when they have not moved, and can then move either one or two squares forward. Pawns CANNOT move through
// other pieces.
//
// To capture, pawns move diagonally forward (one up and one left or right) and they capture the piece on that square.
// Pawns have a special capturing rule called "en passent" which allows them to capture a pawn that has just moved
// forward two squares by moving behind it (capture motion is same as usual but the pawn they are capturing is not on
// the square they move to but the square originally directly beside the capturing pawn)
//
// Pawns are also special because they can promote to any other piece if they reach the opponents back rank
public class Pawn extends ChessPiece {


    public Pawn(int x, int y, User owner) {
        super(x, y, owner);
        value = 1;
    }

    public Pawn(int x, int y, User owner, boolean isCaptured, boolean hasMoved) {
        super(x, y, owner, isCaptured, hasMoved);
        value = 1;
    }

    @Override
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        if (!(abs(x - this.getPosX()) == 0)) {
            return false;
        } else if (hasMoved) {
            if (abs(y - this.getPosY()) >= 2) {
                return false;
            }
        }
        if (abs(y - this.getPosY()) > 2) {
            return false;
        } else if (this.checkBackwards(y)) {
            return false;
        }
        if (y == this.getPosY()) {
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
    //CURRENTLY DOES NOT INCLUDE EN PASSENT!!!
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        if (this.getOwner().isPlayingWhite()) {
            if (!(abs(x - this.getPosX()) == 1) || !((y - this.getPosY()) == 1)) {
                return false;
            }
        } else if (!(this.getOwner().isPlayingWhite())) {
            if (!(abs(x - this.getPosX()) == 1) || !((y - this.getPosY()) == -1)) {
                return false;
            }
        }
        if (!(checkIsOccupied(active, x, y))) {
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
        int dirY;
        dirY = (y - this.getPosY()) / abs(y - this.getPosY());
        //stopX length == stopsY length and each position corresponds to each other
        ArrayList<Integer> stopsX = new ArrayList<>();
        ArrayList<Integer> stopsY = new ArrayList<>();
        genStops(dirX, dirY, stopsX, stopsY, x, y);
        return checkBlocked(active, stopsX, stopsY);
    }

    //EFFECTS: Returns true if the piece is attempting to move backwards in y
    private boolean checkBackwards(int y) {
        if (this.getOwner().isPlayingWhite()) {
            return ((y - this.getPosY()) < 0);
        } else {
            return ((y - this.getPosY()) > 0);
        }
    }

}
