package model;

import java.util.ArrayList;

// A translator object allows for the translation of "Chess speak" into actions
// For example:
//          -"e4" should be interpreted as move pawn to e4
//          -"Ng7" should be interpreted as move Knight to g7
//          -"Qxe8" should be interpreted as capture the piece on e8 with the Queen that is able to perform that action
//          -"Rae8" should be interpreted as capture the piece on e8 with the Rook on the a-file
//          -"0-0" should be interpreted as castle king side
//          -"0-0-0" should be interpreted as castle queen side
//          -"e8=Q" should be interpreted as the pawn moving to e8 and promoting to a queen
//          -"Be4+" should be interpreted as move the Bishop to e4 with check on opposition king
//          -"Kh1#" should be interpreted as move the King to h1 with checkmate on opposition king, ending the game
public class Translator {
    public Translator() {}

    //MODIFIES: !!!many things???
    //EFFECTS: plays out all of the actions that are associated with a given chess command
    public void translateToAction(String move) {
        //stub
    }

    //REQUIRES: given chess coordinate is a valid coordinate
    //EFFECTS: takes a chess coordinate and gives the resulting x coordinate on a board
    public int translateToXCoord(String coord) {
        return 0;  //stub
    }

    //REQUIRES: given chess coordinate is a valid coordinate
    //EFFECTS: takes a chess coordinate and gives the resulting y coordinate on a board
    public int translateToYCoord(String coord) {
        return 0;   //stub
    }

    //REQUIRES: given coordinates are located within bounds of a chessboard
    //EFFECTS: takes board x and y positions and translates to chess coordinates
    public String translateToChessCoord(int x, int y) {
        return ""; //stub
    }
}
