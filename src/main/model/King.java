package model;

import java.util.ArrayList;

import static java.lang.Math.abs;

// Extends the ChessPiece class to include specific information about the king. The king may move up to 1 square in the
// x and y directions. To capture, the king simply needs to move to an occupied square and they capture the piece on the
// square.
public class King extends ChessPiece {

    public King(int x, int y, User owner) {
        super(x, y, owner);
        value = 10;
    }

    public King(int x, int y, User owner, boolean isCaptured, boolean hasMoved) {
        super(x, y, owner, isCaptured, hasMoved);
        value = 10;
    }

    @Override
    //NEED TO ADD FUNCTIONALITY TO NOT ALLOW MOVES ONTO THREATENED SQUARES
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        if ((abs(x - this.getPosX()) > 1) || (abs(y - this.getPosY()) > 1)) {
            return false;
        } else if (x == this.getPosX() && y == this.getPosY()) {
            return false;
        } else if (x < 0 || x > 7) {
            return false;
        } else if (y < 0 || y > 7) {
            return false;
        } else {
            return (!(checkIsOccupied(active, x, y)));
        }
    }

    @Override
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        if ((abs(x - this.getPosX()) > 1) || (abs(y - this.getPosY()) > 1)) {
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

