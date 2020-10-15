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
//
// For the time being this class only has the ability to translate positions such as "e4" back and forth between
// their chess speak and their coordinates on the board
public class Translator {
    public Translator() {}

    //REQUIRES: given chess coordinate is a valid coordinate (length = 2) (within [0,7])
    //EFFECTS: takes a chess coordinate and gives the resulting x coordinate on a board
    public int translateToXCoord(String coord) {
        String posX = coord.substring(0, 1);
        if (posX.equals("a")) {
            return 0;
        } else if (posX.equals("b")) {
            return 1;
        } else if (posX.equals("c")) {
            return 2;
        } else if (posX.equals("d")) {
            return 3;
        } else if (posX.equals("e")) {
            return 4;
        } else if (posX.equals("f")) {
            return 5;
        } else if (posX.equals("g")) {
            return 6;
        } else {
            return 7;
        }
    }

    //REQUIRES: given chess coordinate is a valid coordinate (length = 2) (within [0,7])
    //EFFECTS: takes a chess coordinate and gives the resulting y coordinate on a board
    public int translateToYCoord(String coord) {
        String posY = coord.substring(1, 2);
        return (Integer.parseInt(posY) - 1);
    }

    //REQUIRES: given coordinates are located within bounds of a chessboard (within [0,7])
    //EFFECTS: takes board x and y positions and translates to chess coordinates
    public String translateToChessCoord(int x, int y) {
        String posX = null;
        String posY = null;
        if (x == 0) {
            posX = "a";
        } else if (x == 1) {
            posX = "b";
        } else if (x == 2) {
            posX = "c";
        } else if (x == 3) {
            posX = "d";
        } else if (x == 4) {
            posX = "e";
        } else if (x == 5) {
            posX = "f";
        } else if (x == 6) {
            posX = "g";
        } else {
            posX = "h";
        }
        posY = Integer.toString(y + 1);
        return (posX + posY);
    }
}
