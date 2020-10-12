package model;

import java.util.ArrayList;

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
        return false;
    }

    @Override
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        return null;
    }
}
