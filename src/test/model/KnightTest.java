package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest extends ChessPieceTest {


    @BeforeEach
    public void setup() {
        setupPieces();
        setupTargets();
        active = new ArrayList<>();
    }

    @Test
    public void printSymbolWhiteTest() {
        assertEquals('N', tester.symbol());
    }

    @Test
    public void printSymbolBlackTest() {
        assertEquals('n', enemyTester.symbol());
    }

    //Knights cannot be blocked so original tests do not apply
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
        pieceValue = 3;
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
        tester = new Knight(testerPosX, testerPosY, owner);
        boundTester = new Knight(boundTesterPosX, boundTesterPosY, owner);
        enemyTester = new Knight(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        enemyBoundTester = new Knight(enemyBoundTesterPosX, enemyBoundTesterPosY, enemyOwner);
    }

    private void setupTargets() {
        illegalTargetPosX = 3;
        illegalTargetPosY = 5;
        targetPosX = 2;
        targetPosY = 1;
        belowBoundaryXTargetPosX = -1;
        belowBoundaryXTargetPosY = 2;
        lowBoundaryXTargetPosX = 0;
        lowBoundaryXTargetPosY = 3;
        highBoundaryXTargetPosX = 7;
        highBoundaryXTargetPosY = 4;
        aboveBoundaryXTargetPosX = 8;
        aboveBoundaryXTargetPosY = 5;
        belowBoundaryYTargetPosX = 2;
        belowBoundaryYTargetPosY = -1;
        lowBoundaryYTargetPosX = 3;
        lowBoundaryYTargetPosY = 0;
        highBoundaryYTargetPosX = 4;
        highBoundaryYTargetPosY = 7;
        aboveBoundaryYTargetPosX = 6;
        aboveBoundaryYTargetPosY = 9;
    }
}
