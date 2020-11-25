package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest extends ChessPieceTest {

    @BeforeEach
    public void setup() {
        setupPieces();
        setupTargets();
        active = new ArrayList<>();
    }

    @Test
    public void printSymbolWhiteTest() {
        assertEquals("K", tester.symbol());
    }

    @Test
    public void printSymbolBlackTest() {
        assertEquals("k", enemyTester.symbol());
    }

    //Kings cannot be blocked since they only move 1 square, and this case falls under occupy tests
    // so original tests do not apply
    @Override
    public void isLegalMoveBlockedFriendlyTest() {}

    @Override
    public void isLegalMoveBlockedEnemyTest() {}

    @Override
    public void isLegalCaptureBlockedFriendlyTest() {}

    @Override
    public void isLegalCaptureBlockedEnemyTest() {}

    //Helpers for setup method found below
    private void setupPieces() {
        pieceValue = 10;
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
        tester = new King(testerPosX, testerPosY, owner);
        boundTester = new King(boundTesterPosX, boundTesterPosY, owner);
        enemyTester = new King(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        enemyBoundTester = new King(enemyBoundTesterPosX, enemyBoundTesterPosY, enemyOwner);
    }

    private void setupTargets() {
        illegalTargetPosX = 3;
        illegalTargetPosY = 5;
        targetPosX = 1;
        targetPosY = 1;
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
        highBoundaryYTargetPosX = 5;
        highBoundaryYTargetPosY = 7;
        aboveBoundaryYTargetPosX = 6;
        aboveBoundaryYTargetPosY = 8;
    }
}
