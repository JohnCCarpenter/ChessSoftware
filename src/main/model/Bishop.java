package model;

import java.util.ArrayList;

// Extends the ChessPiece class to include specific information about bishops. Bishops may only move along diagonals
// (move equal amounts in x and y) and CANNOT move through pieces. To capture, bishops simply need to move to an
// occupied square and they capture the piece on the square.
public class Bishop extends ChessPiece {

    public Bishop(int x, int y, User owner) {
        super(x, y, owner);
        value = 3;
    }

    @Override
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        return false;
    }

    @Override
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        return null;
    }
}
