package model;

import java.util.ArrayList;

// Extends the ChessPiece class to include specific information about Queens. Queens may either move along diagonals
// or straight lines (equal amounts in x and y OR any number of squares in one of x or y) and CANNOT move through pieces
// To capture, queens simply need to move to an occupied square and they capture the piece on that square.
public class Queen extends ChessPiece {


    public Queen(int x, int y, User owner) {
        super(x, y, owner);
        value = 9;
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
