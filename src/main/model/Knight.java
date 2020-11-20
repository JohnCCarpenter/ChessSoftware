package model;

import java.util.ArrayList;

import static java.lang.Math.abs;

// Extends the ChessPiece class to include specific information about knights. Knights may only move along L-shapes
// (move 2 squares in one of x or y and 1 in the other) and CAN move through pieces.
// To capture, knights simply need to move to an occupied square and they capture the piece on that square.
public class Knight extends ChessPiece {


    public Knight(int x, int y, User owner) {
        super(x, y, owner);
        value = 3;
    }

    public Knight(int x, int y, User owner, boolean isCaptured, boolean hasMoved) {
        super(x, y, owner, isCaptured, hasMoved);
        value = 3;
    }

    @Override
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        int diffX = x - this.getPosX();
        int diffY = y - this.getPosY();
        if (!(((abs(diffX) == 2) && (abs(diffY) == 1)) || ((abs(diffX) == 1) && (abs(diffY) == 2)))) {
            return false;
        } else if (x < 0 || x > 7) {
            return false;
        } else if (y < 0 || y > 7) {
            return false;
        } else {
            return !(checkIsOccupied(active, x, y));
        }
    }

    @Override
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        int diffX = x - this.getPosX();
        int diffY = y - this.getPosY();
        if (!(((abs(diffX) == 2) && (abs(diffY) == 1)) || ((abs(diffX) == 1) && (abs(diffY) == 2)))) {
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
}
