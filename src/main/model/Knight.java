package model;

import java.util.ArrayList;

// Extends the ChessPiece class to include specific information about knights. Knights may only move along L-shapes
// (move 2 squares in one of x or y and 1 in the other) and CAN move through pieces.
// To capture, knights simply need to move to an occupied square and they capture the piece on that square.
public class Knight extends ChessPiece {


    public Knight(int x, int y, User owner) {
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
