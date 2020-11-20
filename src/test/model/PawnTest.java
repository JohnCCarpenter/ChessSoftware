package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest extends ChessPieceTest {
    int captureTargetX;
    int captureTargetY;
    int blackCaptureTargetX;
    int blackCaptureTargetY;
    int singleMoveTargetX;
    int singleMoveTargetY;
    int backwardsMoveTargetX;
    int backwardsMoveTargetY;
    int blackBackwardsMoveTargetX;
    int blackBackwardsMoveTargetY;
    int sidewaysMoveTargetX;
    int sidewaysMoveTargetY;

    @BeforeEach
    public void setup() {
        setupPieces();
        setupTargets();
        setupPawnSpecifics();
        active = new ArrayList<>();
    }

    @Test
    public void printSymbolWhiteTest() {
        assertEquals("P", tester.symbol());
    }

    @Test
    public void printSymbolBlackTest() {
        assertEquals("p", enemyTester.symbol());
    }

    //NEW PAWN TESTS
        //Testing for the captures method
    @Test
    public void capturesLegalTest() {
        occupier = new Pawn (captureTargetX, captureTargetY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertTrue(tester.captures(active, captureTargetX, captureTargetY));
        assertEquals(captureTargetX, tester.getPosX());
        assertEquals(captureTargetY, tester.getPosY());
        assertTrue(occupier.getIsCaptured());
        assertFalse(tester.getIsCaptured());
        assertFalse(active.contains(occupier));
        assertTrue(active.contains(tester));
        assertTrue(owner.getCaptured().contains(occupier));

    }

    //UPDATED PAWN TESTS
    //Since pawns can only move one square at a time there are no blocking or capturing cases
    //except for first movement cases where pawn moves 2 squares
    @Override
    public void isLegalMoveBlockedFriendlyTest() {
        blocker = new Pawn(blockerPosX, blockerPosY, owner);
        active.add(blocker);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY+1));
    }

    @Override
    public void isLegalMoveBlockedEnemyTest() {
        blocker = new Pawn(blockerPosX, blockerPosY, enemyOwner);
        active.add(blocker);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY+1));
    }

    //All horizontal movements will be covered under illegal moves so out of horizontal bounds is not needed to test
    @Override
    public void isLegalMoveBelowXTest() { }

    @Override
    public void isLegalMoveAboveXTest() { }

    //Other bounds need to be used with different starting positions, naming may not perfectly represent goal of piece
    @Override
    public void isLegalMoveHighBoundXTest() {
        active.add(enemyTester);
        assertTrue(enemyTester.isLegalMove(active, highBoundaryXTargetPosX, highBoundaryXTargetPosY));
    }

    @Override
    public void isLegalMoveLowBoundXTest() {
        active.add(tester);
        assertTrue(tester.isLegalMove(active, lowBoundaryXTargetPosX, lowBoundaryXTargetPosY));
    }
    @Override
    public void isLegalMoveBelowYTest() {
        active.add(enemyBoundTester);
        assertFalse(enemyBoundTester.isLegalMove(active, belowBoundaryYTargetPosX, belowBoundaryYTargetPosY));
    }

    @Override
    public void isLegalMoveAboveYTest() {
        active.add(boundTester);
        assertFalse(boundTester.isLegalMove(active, aboveBoundaryYTargetPosX, aboveBoundaryYTargetPosY));
    }

    @Override
    public void isLegalMoveHighBoundYTest() {
        active.add(boundTester);
        assertTrue(boundTester.isLegalMove(active, highBoundaryYTargetPosX, highBoundaryYTargetPosY));
    }

    @Override
    public void isLegalMoveLowBoundYTest() {
        active.add(enemyBoundTester);
        assertTrue(enemyBoundTester.isLegalMove(active, lowBoundaryYTargetPosX, lowBoundaryYTargetPosY));
    }

    //Rewriting capture tests
    //All captures will not go through any blocked squares
    @Override
    public void isLegalCaptureBlockedFriendlyTest() { }

    @Override
    public void isLegalCaptureBlockedEnemyTest() { }

    @Override
    public void isLegalCaptureOccupiedFriendlyTest() {
        occupier = new Pawn(captureTargetX, captureTargetY, owner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalCapture(active, captureTargetX, captureTargetY));
    }

    @Override
    public void isLegalCaptureEmptyTargetTest() {
        active.add(tester);
        assertFalse(tester.isLegalCapture(active, captureTargetX, captureTargetY));
    }

    @Override
    public void isLegalCaptureSucceedTest() {
        occupier = new Pawn(captureTargetX, captureTargetY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, captureTargetX, captureTargetY));
    }

    //NEW PAWN TESTS
    @Test
    public void isLegalCaptureForwardTest() {
        occupier = new Pawn(targetPosX, targetPosY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalCapture(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalCaptureBlackSucceedTest() {
        occupier = new Pawn(blackCaptureTargetX, blackCaptureTargetY, owner);
        active.add(occupier);
        active.add(enemyTester);
        assertTrue(enemyTester.isLegalCapture(active, blackCaptureTargetX, blackCaptureTargetY));
    }

    @Test
    public void isLegalCaptureBlackFailTest() {
        occupier = new Pawn(blackCaptureTargetX, blackCaptureTargetY, owner);
        active.add(occupier);
        active.add(enemyTester);
        assertFalse(enemyTester.isLegalCapture(active, illegalTargetPosX, illegalTargetPosY));
    }

    @Test
    public void isLegalMoveBackwardsTest() {
        active.add(boundTester);
        assertFalse(boundTester.move(active, backwardsMoveTargetX, backwardsMoveTargetY));
    }

    @Test
    public void isLegalMoveBackwardsBlackTest() {
        active.add(enemyBoundTester);
        assertFalse(enemyBoundTester.move(active, blackBackwardsMoveTargetX, blackBackwardsMoveTargetY));
    }

    @Test
    public void isLegalMoveSidewaysTest() {
        active.add(boundTester);
        assertFalse(boundTester.move(active, sidewaysMoveTargetX, sidewaysMoveTargetY));
    }

    @Test
    public void isLegalMoveDoubleHasMovedTest() {
        active.add(tester);
        tester.setHasMoved(true);
        assertTrue(tester.getHasMoved());
        assertFalse(tester.move(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalMoveSingleTest() {
        active.add(tester);
        tester.setHasMoved(true);
        assertTrue(tester.getHasMoved());
        assertTrue(tester.move(active, singleMoveTargetX, singleMoveTargetY));
    }

    //Helpers for setup method found below
    private void setupPieces() {
        pieceValue = 1;
        testerPosX = 0;
        testerPosY = 0;
        enemyTesterPosX = 7;
        enemyTesterPosY = 7;
        boundTesterPosX = 6;
        boundTesterPosY = 6;
        enemyBoundTesterPosX = 1;
        enemyBoundTesterPosY = 1;
        blockerPosX = 1;
        blockerPosY = 0;
        tester = new Pawn(testerPosX, testerPosY, owner);
        boundTester = new Pawn(boundTesterPosX, boundTesterPosY, owner);
        enemyTester = new Pawn(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        enemyBoundTester = new Pawn(enemyBoundTesterPosX, enemyBoundTesterPosY, enemyOwner);
    }

    private void setupTargets() {
        illegalTargetPosX = 0;
        illegalTargetPosY = 3;
        targetPosX = 0;
        targetPosY = 2;
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
        lowBoundaryYTargetPosX = 1;
        lowBoundaryYTargetPosY = 0;
        highBoundaryYTargetPosX = 6;
        highBoundaryYTargetPosY = 7;
        aboveBoundaryYTargetPosX = 6;
        aboveBoundaryYTargetPosY = 8;
    }

    private void setupPawnSpecifics() {
        captureTargetX = 1;
        captureTargetY = 1;
        blackCaptureTargetX = 6;
        blackCaptureTargetY = 6;
        singleMoveTargetX = 0;
        singleMoveTargetY = 1;
        backwardsMoveTargetX = 6;
        backwardsMoveTargetY = 5;
        blackBackwardsMoveTargetX = 1;
        blackBackwardsMoveTargetY = 2;
        sidewaysMoveTargetX = 7;
        sidewaysMoveTargetY = 6;
    }
}
