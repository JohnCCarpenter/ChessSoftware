package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ChessPieceTest {

    //The following are fields to be specified in subclasses so that tests may run properly

    //Value assigned to each piece of the type
    protected int pieceValue;
    //Initial coordinates of tester piece of type class
    protected int testerPosX;
    protected int testerPosY;
    //Initial coordinates of tester piece of type class with a different owner/position
    protected int enemyTesterPosX;
    protected int enemyTesterPosY;
    //Initial coordinates of boundTester piece of type class
    protected int boundTesterPosX;
    protected int boundTesterPosY;
    //Initial coordinates of boundTester piece of type class with a different owner/position
    protected int enemyBoundTesterPosX;
    protected int enemyBoundTesterPosY;
    //Coordinates of a piece that is in between the tester piece and its target destination
    protected int blockerPosX;
    protected int blockerPosY;
    //Coordinates of an illegal movement for the piece
    protected int illegalTargetPosX;
    protected int illegalTargetPosY;
    //Coordinates of a legal movement for the piece, with space in between for a blocker if applicable
    //  must also be squarely within the bounds of the chessboard (not boundary case)
    protected int targetPosX;
    protected int targetPosY;
    //Legal move to a position with x out of bounds below range of chessboard
    protected int belowBoundaryXTargetPosX;
    protected int belowBoundaryXTargetPosY;
    //Legal move to a position with x on the lower boundary of chessboard
    protected int lowBoundaryXTargetPosX;
    protected int lowBoundaryXTargetPosY;
    //Legal move to a position with x on the upper boundary of chessboard
    protected int highBoundaryXTargetPosX;
    protected int highBoundaryXTargetPosY;
    //Legal move to a position with x out of bounds above  boundary of chessboard
    protected int aboveBoundaryXTargetPosX;
    protected int aboveBoundaryXTargetPosY;
    //Legal move to a position with y out of bounds below range of chessboard
    protected int belowBoundaryYTargetPosX;
    protected int belowBoundaryYTargetPosY;
    //Legal move to a position with y on the lower boundary of chessboard
    protected int lowBoundaryYTargetPosX;
    protected int lowBoundaryYTargetPosY;
    //Legal move to a position with y on the upper boundary of chessboard
    protected int highBoundaryYTargetPosX;
    protected int highBoundaryYTargetPosY;
    //Legal move to a position with y out of bounds above boundary of chessboard
    protected int aboveBoundaryYTargetPosX;
    protected int aboveBoundaryYTargetPosY;

    ChessPiece tester;
    ChessPiece boundTester;
    ChessPiece enemyTester;
    ChessPiece enemyBoundTester;
    ChessPiece blocker;
    //Occupier has the same coordinates as legal target move
    ChessPiece occupier;
    ArrayList<ChessPiece> active;
    User owner = new User(true);
    User enemyOwner = new User(false);

    @Test
    //Test that objects get instantiated properly so initialization doesn't need to be checked within later tests
    public void constructorTest() {
        assertEquals(testerPosX, tester.getPosX());
        assertEquals(testerPosY, tester.getPosY());
        assertEquals(enemyTesterPosX, enemyTester.getPosX());
        assertEquals(enemyTesterPosY, enemyTester.getPosY());
        assertEquals(owner, tester.getOwner());
        assertEquals(enemyOwner, enemyTester.getOwner());
        assertEquals(pieceValue, tester.getValue());
        assertFalse(tester.getIsCaptured());
        assertFalse(tester.getHasMoved());
    }

    //Testing for the move method
    @Test
    public void moveLegalTest() {
        active.add(tester);
        assertTrue(tester.move(active, targetPosX, targetPosY));
        assertEquals(targetPosX, tester.getPosX());
        assertEquals(targetPosY, tester.getPosY());
    }

    @Test
    public void moveIllegalTest() {
        active.add(tester);
        assertFalse(tester.move(active, illegalTargetPosX, illegalTargetPosY));
        assertEquals(testerPosX, tester.getPosX());
        assertEquals(testerPosY, tester.getPosY());
    }

    //Testing for the captures method
    @Test
    public void capturesLegalTest() {
        occupier = new Pawn (targetPosX, targetPosY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertTrue(tester.captures(active, targetPosX, targetPosY));
        assertEquals(targetPosX, tester.getPosX());
        assertEquals(targetPosY, tester.getPosY());
        assertTrue(occupier.getIsCaptured());
        assertFalse(tester.getIsCaptured());
        assertFalse(active.contains(occupier));
        assertTrue(active.contains(tester));
        assertTrue(owner.getCaptured().contains(occupier));

    }

    @Test
    public void capturesIllegalTest() {
        occupier = new Pawn (illegalTargetPosX, illegalTargetPosY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.captures(active, targetPosX, targetPosY));
        assertEquals(testerPosX, tester.getPosX());
        assertEquals(testerPosY, tester.getPosY());
        assertFalse(occupier.getIsCaptured());
        assertFalse(tester.getIsCaptured());
        assertTrue(active.contains(occupier));
        assertTrue(active.contains(tester));
        assertFalse(owner.getCaptured().contains(occupier));
    }

    // Testing for the isLegalMove Method
    @Test
    public void isLegalMoveSelfTest() {
        active.add(tester);
        assertFalse(tester.isLegalMove(active, tester.getPosX(), tester.getPosY()));
    }

    @Test
    public void isLegalMoveSelfDifValueTest() {
        active.add(tester);
        tester.setPosX(illegalTargetPosX);
        tester.setPosY(illegalTargetPosY);
        assertEquals(illegalTargetPosX, tester.getPosX());
        assertEquals(illegalTargetPosY, tester. getPosY());
        assertFalse(tester.isLegalMove(active, tester.getPosX(), tester.getPosY()));
    }

    @Test
    public void isLegalMoveBlockedFriendlyTest() {
        blocker = new Pawn(blockerPosX, blockerPosY, owner);
        active.add(blocker);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalMoveBlockedEnemyTest() {
        blocker = new Pawn(blockerPosX, blockerPosY, enemyOwner);
        active.add(blocker);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalMoveOccupiedFriendlyTest() {
        occupier = new Pawn(targetPosX, targetPosY, owner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalMoveOccupiedEnemyTest() {
        occupier = new Pawn(targetPosX, targetPosY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalMoveBadMovementTest() {
        active.add(tester);
        assertFalse(tester.isLegalMove(active, illegalTargetPosX, illegalTargetPosY));
    }

    //The next 8 test are testing the boundary cases on the board
    @Test
    public void isLegalMoveBelowXTest() {
        active.add(tester);
        assertFalse(tester.isLegalMove(active, belowBoundaryXTargetPosX, belowBoundaryXTargetPosY));
    }

    @Test
    public void isLegalMoveAboveXTest() {
        active.add(enemyTester);
        assertFalse(enemyTester.isLegalMove(active, aboveBoundaryXTargetPosX, aboveBoundaryXTargetPosY));
    }

    @Test
    public void isLegalMoveHighBoundXTest() {
        active.add(boundTester);
        assertTrue(boundTester.isLegalMove(active, highBoundaryXTargetPosX, highBoundaryXTargetPosY));
    }

    @Test
    public void isLegalMoveLowBoundXTest() {
        active.add(enemyBoundTester);
        assertTrue(enemyBoundTester.isLegalMove(active, lowBoundaryXTargetPosX, lowBoundaryXTargetPosY));
    }
    @Test
    public void isLegalMoveBelowYTest() {
        active.add(tester);
        assertFalse(tester.isLegalMove(active, belowBoundaryYTargetPosX, belowBoundaryYTargetPosY));
    }

    @Test
    public void isLegalMoveAboveYTest() {
        active.add(enemyTester);
        assertFalse(enemyTester.isLegalMove(active, aboveBoundaryYTargetPosX, aboveBoundaryYTargetPosY));
    }

    @Test
    public void isLegalMoveHighBoundYTest() {
        active.add(boundTester);
        assertTrue(boundTester.isLegalMove(active, highBoundaryYTargetPosX, highBoundaryYTargetPosY));
    }

    @Test
    public void isLegalMoveLowBoundYTest() {
        active.add(enemyBoundTester);
        assertTrue(enemyBoundTester.isLegalMove(active, lowBoundaryYTargetPosX, lowBoundaryYTargetPosY));
    }

    //Non boundary legal move
    @Test
    public void isLegalMoveSucceedTest() {
        active.add(tester);
        assertTrue(tester.isLegalMove(active, targetPosX, targetPosY));
    }


    // Testing for the isLegalCapture Method, note that boundary tests aren't needed as pieces cannot be located out of
    // bounds so any failure for capturing out of bounds will fail on having an empty target at all times
    @Test
    public void isLegalCaptureBlockedFriendlyTest() {
        blocker = new Pawn(blockerPosX, blockerPosY, owner);
        occupier = new Pawn(targetPosX, targetPosY, enemyOwner);
        active.add(blocker);
        active.add(tester);
        active.add(occupier);
        assertFalse(tester.isLegalCapture(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalCaptureBlockedEnemyTest() {
        blocker = new Pawn(blockerPosX, blockerPosY, enemyOwner);
        occupier = new Rook(targetPosX, targetPosY, enemyOwner);
        active.add(blocker);
        active.add(tester);
        active.add(occupier);
        assertFalse(tester.isLegalCapture(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalCaptureOccupiedFriendlyTest() {
        occupier = new Pawn(targetPosX, targetPosY, owner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalCapture(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalCaptureEmptyTargetTest() {
        active.add(tester);
        assertFalse(tester.isLegalCapture(active, targetPosX, targetPosY));
    }

    @Test
    public void isLegalCaptureBadMoveTest() {
        active.add(tester);
        assertFalse(tester.isLegalCapture(active, illegalTargetPosX, illegalTargetPosY));
    }

    @Test
    public void isLegalCaptureSucceedTest() {
        occupier = new Pawn(targetPosX, targetPosY, enemyOwner);
        active.add(occupier);
        active.add(tester);
        assertFalse(tester.isLegalMove(active, targetPosX, targetPosY));
    }

/*    //Testing for the promote method
    @Test
    public void promoteRook() {
        ChessPiece newPiece = new Rook(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        active.add(tester);
        tester.promote("Rook", active);
        assertEquals(testerPosX, newPiece.getPosX());
        assertEquals(testerPosY, newPiece.getPosY());
        assertEquals(owner, newPiece.getOwner());
        assertTrue(active.contains(newPiece));
        assertFalse(active.contains(tester));
        assertTrue(newPiece instanceof Rook);
    }

    @Test
    public void promoteKnight() {
        active.add(tester);
        tester.promote("Knight", active);
        assertEquals(testerPosX, tester.newPiece.getPosX());
        assertEquals(testerPosY, tester.newPiece.getPosY());
        assertEquals(owner, tester.newPiece.getOwner());
        assertTrue(active.contains(tester.newPiece));
        assertFalse(active.contains(tester));
        assertTrue(tester.newPiece instanceof Knight);
    }

    @Test
    public void promoteBishop() {
        ChessPiece newPiece = new Bishop(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        active.add(tester);
        tester.promote("Bishop", active);
        assertEquals(testerPosX, newPiece.getPosX());
        assertEquals(testerPosY, newPiece.getPosY());
        assertEquals(owner, newPiece.getOwner());
        assertTrue(active.contains(newPiece));
        assertFalse(active.contains(tester));
        assertTrue(newPiece instanceof Bishop);
    }

    @Test
    public void promoteQueen() {
        ChessPiece newPiece = new Queen(enemyTesterPosX, enemyTesterPosY, enemyOwner);
        active.add(tester);
        tester.promote("Queen", active);
        assertEquals(testerPosX, newPiece.getPosX());
        assertEquals(testerPosY, newPiece.getPosY());
        assertEquals(owner, newPiece.getOwner());
        assertTrue(active.contains(newPiece));
        assertFalse(active.contains(tester));
        assertTrue(newPiece instanceof Queen);
    }*/
}
