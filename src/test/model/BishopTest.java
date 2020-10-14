package model;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class BishopTest extends ChessPieceTest {


    @BeforeEach
    public void setup() {
        setupPieces();
        setupTargets();
        active = new ArrayList<ChessPiece>();
    }

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
        tester = new Bishop(testerPosX, testerPosY, owner);
        boundTester = new Bishop(boundTesterPosX, boundTesterPosY, owner);
        enemyTester = new Bishop(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        enemyBoundTester = new Bishop(enemyBoundTesterPosX, enemyBoundTesterPosY, enemyOwner);
    }

    private void setupTargets() {
        illegalTargetPosX = 3;
        illegalTargetPosY = 5;
        targetPosX = 5;
        targetPosY = 5;
        belowBoundaryXTargetPosX = -1;
        belowBoundaryXTargetPosY = 1;
        lowBoundaryXTargetPosX = 0;
        lowBoundaryXTargetPosY = 2;
        highBoundaryXTargetPosX = 7;
        highBoundaryXTargetPosY = 5;
        aboveBoundaryXTargetPosX = 8;
        aboveBoundaryXTargetPosY = 6;
        belowBoundaryYTargetPosX = 1;
        belowBoundaryYTargetPosY = -1;
        lowBoundaryYTargetPosX = 2;
        lowBoundaryYTargetPosY = 0;
        highBoundaryYTargetPosX = 5;
        highBoundaryYTargetPosY = 7;
        aboveBoundaryYTargetPosX = 6;
        aboveBoundaryYTargetPosY = 8;
    }
}
