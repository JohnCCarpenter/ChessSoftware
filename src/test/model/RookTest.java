package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest extends ChessPieceTest {


    @BeforeEach
    public void setup() {
        setupPieces();
        setupTargets();
        active = new ArrayList<>();
    }

    @Test
    public void printSymbolWhiteTest() {
        assertEquals('R', tester.printSymbol());
    }

    @Test
    public void printSymbolBlackTest() {
        assertEquals('r', enemyTester.printSymbol());
    }

    //Helpers for setup method found below
    private void setupPieces() {
        pieceValue = 5;
        testerPosX = 0;
        testerPosY = 0;
        enemyTesterPosX = 7;
        enemyTesterPosY = 7;
        boundTesterPosX = 6;
        boundTesterPosY = 6;
        enemyBoundTesterPosX = 1;
        enemyBoundTesterPosY = 1;
        blockerPosX = 3;
        blockerPosY = 0;
        tester = new Rook(testerPosX, testerPosY, owner);
        boundTester = new Rook(boundTesterPosX, boundTesterPosY, owner);
        enemyTester = new Rook(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        enemyBoundTester = new Rook(enemyBoundTesterPosX, enemyBoundTesterPosY, enemyOwner);
    }

    private void setupTargets() {
        illegalTargetPosX = 3;
        illegalTargetPosY = 5;
        targetPosX = 5;
        targetPosY = 0;
        belowBoundaryXTargetPosX = -1;
        belowBoundaryXTargetPosY = 0;
        lowBoundaryXTargetPosX = 0;
        lowBoundaryXTargetPosY = 1;
        highBoundaryXTargetPosX = 7;
        highBoundaryXTargetPosY = 6;
        aboveBoundaryXTargetPosX = 8;
        aboveBoundaryXTargetPosY = 7;
        belowBoundaryYTargetPosX = 0;
        belowBoundaryYTargetPosY = -1;
        lowBoundaryYTargetPosX = 1;
        lowBoundaryYTargetPosY = 0;
        highBoundaryYTargetPosX = 6;
        highBoundaryYTargetPosY = 7;
        aboveBoundaryYTargetPosX = 7;
        aboveBoundaryYTargetPosY = 9;
    }
}
