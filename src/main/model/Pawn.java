package model;

import java.util.ArrayList;

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

    @Override
    public boolean isLegalMove(ArrayList<ChessPiece> active, int x, int y) {
        return false;
    }

    @Override
    public Boolean isLegalCapture(ArrayList<ChessPiece> active, int x, int y) {
        return null;
    }

    //REQUIRES: pawn this is called on is on the opponents back rank
    //MODIFIES: this and promotedPiece
    //EFFECTS: removes this pawn from play and replaces it with a new piece of type upgrade called promotedPiece
    //         if multiple pieces have been promoted name them promotedPiecen where n is the number of promotions
    public void promote(String upgrade) {
        //stub
    }

}
