package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest extends ChessPieceTest {


    @BeforeEach
    public void setup() {
        setupPieces();
        setupTargets();
        active = new ArrayList<>();
    }

    @Test
    public void printSymbolWhiteTest() {
        assertEquals("Q", tester.symbol());
    }

    @Test
    public void printSymbolBlackTest() {
        assertEquals("q", enemyTester.symbol());
    }

    //Helpers for setup method found below
    private void setupPieces() {
        pieceValue = 9;
        testerPosX = 0;
        testerPosY = 0;
        enemyTesterPosX = 7;
        enemyTesterPosY = 7;
        boundTesterPosX = 6;
        boundTesterPosY = 6;
        enemyBoundTesterPosX = 1;
        enemyBoundTesterPosY = 1;
        blockerPosX = 2;
        blockerPosY = 2;
        tester = new Queen(testerPosX, testerPosY, owner);
        boundTester = new Queen(boundTesterPosX, boundTesterPosY, owner);
        enemyTester = new Queen(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        enemyBoundTester = new Queen(enemyBoundTesterPosX, enemyBoundTesterPosY, enemyOwner);
    }

    private void setupTargets() {
        illegalTargetPosX = 5;
        illegalTargetPosY = 6;
        targetPosX = 5;
        targetPosY = 5;
        belowBoundaryXTargetPosX = -1;
        belowBoundaryXTargetPosY = 1;
        lowBoundaryXTargetPosX = 0;
        lowBoundaryXTargetPosY = 2;
        highBoundaryXTargetPosX = 7;
        highBoundaryXTargetPosY = 6;
        aboveBoundaryXTargetPosX = 8;
        aboveBoundaryXTargetPosY = 6;
        belowBoundaryYTargetPosX = 1;
        belowBoundaryYTargetPosY = -1;
        lowBoundaryYTargetPosX = 1;
        lowBoundaryYTargetPosY = 0;
        highBoundaryYTargetPosX = 5;
        highBoundaryYTargetPosY = 7;
        aboveBoundaryYTargetPosX = 6;
        aboveBoundaryYTargetPosY = 8;
    }
}
