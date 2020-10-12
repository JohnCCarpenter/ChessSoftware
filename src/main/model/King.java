package model;

import java.util.ArrayList;

// Extends the ChessPiece class to include specific information about the king. The king may move up to 1 square in the
// x and y directions. To capture, the king simply needs to move to an occupied square and they capture the piece on the
// square.
public class King extends ChessPiece {

    public King(int x, int y, User owner) {
        super(x, y, owner);
        value = 10;
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

