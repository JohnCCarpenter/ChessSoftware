package model;

import exceptions.*;
import exceptions.IllegalMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {
    //Coordinates of an illegal movement for the test piece
    protected int illegalTargetPosX;
    protected int illegalTargetPosY;
    //Coordinates of a legal movement for the piece, with space in between for a blocker if applicable
    //  must also be squarely within the bounds of the chessboard (not boundary case)
    protected int targetPosX;
    protected int targetPosY;

    private ChessGame defaultBoard;
    private ChessPiece tester;
    private ChessPiece boundTester;
    private ChessPiece enemyTester;
    private ChessPiece enemyBoundTester;
    private ChessPiece blocker;
    //Occupier has the same coordinates as legal target move
    private ChessPiece occupier;
    private ArrayList<ChessPiece> active;
    private User owner = new User(true, "White");
    private User enemyOwner = new User(false, "Black");

    @BeforeEach
    void setup() {
        defaultBoard = new ChessGame("White", "Black");
    }

    @Test
    void defaultConstructorTest() {
        assertDefaultPawns();
        assertDefaultOthers();
        assertBlackOwned();
        assertWhiteOwned();
    }

    @Test
    void customConstructorTest() {
        ChessPiece white1 = new Pawn(1,3,owner);
        ChessPiece white2 = new Bishop(4,1,owner);
        ChessPiece white3 = new King(6,7,owner);
        ChessPiece black1 = new Queen(0,1,enemyOwner);
        ChessPiece black2 = new King(0,0,enemyOwner);
        ChessPiece black3 = new Rook(7,1,enemyOwner);

        active = new ArrayList<ChessPiece>();

        ChessGame customBoard = new ChessGame(owner, enemyOwner, "e3", false);

        assertEquals("e3", customBoard.getEnPassent());
        assertFalse(customBoard.getIsWhiteTurn());

        assert isContained(customBoard.getBlackPlayer().getOwned(), black1);
        assert isContained(customBoard.getBlackPlayer().getOwned(), black2);
        assert isContained(customBoard.getBlackPlayer().getOwned(), black3);
        assert isContained(customBoard.getWhitePlayer().getOwned(), white1);
        assert isContained(customBoard.getWhitePlayer().getOwned(), white2);
        assert isContained(customBoard.getWhitePlayer().getOwned(), white3);

        assert isContained(customBoard.getActive(), black1);
        assert isContained(customBoard.getActive(), black2);
        assert isContained(customBoard.getActive(), black3);
        assert isContained(customBoard.getActive(), white1);
        assert isContained(customBoard.getActive(), white2);
        assert isContained(customBoard.getActive(), white3);
    }

    @Test
    void returnFriendlyPieceOnSucceedTest() {
        assertEquals(2, defaultBoard.returnFriendlyPieceOn(2, 1).getPosX());
        assertEquals(1, defaultBoard.returnFriendlyPieceOn(2, 1).getPosY());
        assertEquals(owner.getName(), defaultBoard.returnFriendlyPieceOn(2, 1).getOwner().getName());
        assertEquals(owner.isPlayingWhite(), defaultBoard.returnFriendlyPieceOn(2, 1).getOwner().isPlayingWhite());
    }

    @Test
    void returnFriendlyPieceOnNoPieceTest() {
        assertEquals(null, defaultBoard.returnFriendlyPieceOn(3, 3));
    }

    @Test
    void returnFriendlyPieceOnEnemyPieceTest() {
        assertEquals(null, defaultBoard.returnFriendlyPieceOn(7, 7));
    }

    @Test
    void performMoveNoExceptionTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());
        try {
            defaultBoard.performMove("e2", "e4");
        } catch (BadSelectException bse) {
            fail("Caught BadSelectException on good selection");
        } catch (IllegalMovementException ime) {
            fail("Caught IllegalMovementException on good move");
        }
        assertFalse (defaultBoard.getIsWhiteTurn());
    }

    @Test
    void performMoveBadSelectTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());
        try {
            defaultBoard.performMove("e4", "e5");
        } catch (BadSelectException bse) {
            //Do nothing
        } catch (IllegalMovementException ime) {
            fail("Caught IllegalMovementException on good move");
        }
        assertTrue(defaultBoard.getIsWhiteTurn());
    }

    @Test
    void performMoveIllegalMoveTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());
        try {
            defaultBoard.performMove("e2", "e6");
        } catch (BadSelectException bse) {
            fail("Caught BadSelectException on good selection");
        } catch (IllegalMovementException ime) {
            //Do nothing
        }
        assertTrue(defaultBoard.getIsWhiteTurn());
    }

    @Test
    void performCaptureNoExceptionTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());

        try {
            defaultBoard.performMove("e2", "e4");
        } catch (IllegalMovementException e) {
            fail("Movement function not working");
        } catch (BadSelectException e) {
            fail("Movement function not working");
        }

        assertFalse(defaultBoard.getIsWhiteTurn());

        try {
            defaultBoard.performMove("d7", "d5");
        } catch (IllegalMovementException e) {
            fail("Movement function not working");
        } catch (BadSelectException e) {
            fail("Movement function not working");
        }

        assertTrue(defaultBoard.getIsWhiteTurn());

        try {
            defaultBoard.performCapture("e4", "d5");
        } catch (BadSelectException bse) {
            fail("Caught BadSelectException on good selection");
        } catch (IllegalCaptureException ice) {
            fail("Caught IllegalCaptureException on good move");
        }

        assertFalse(defaultBoard.getIsWhiteTurn());
    }

    @Test
    void performCaptureBadSelectTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());
        try {
            defaultBoard.performCapture("e4", "d5");
        } catch (BadSelectException bse) {
            //Do nothing
        } catch (IllegalCaptureException ice) {
            fail("Caught IllegalCaptureException on good move");
        }
        assertTrue(defaultBoard.getIsWhiteTurn());
    }

    @Test
    void performCaptureIllegalMoveTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());
        try {
            defaultBoard.performCapture("e2", "d8");
        } catch (BadSelectException bse) {
            fail("Caught BadSelectException on good selection");
        } catch (IllegalCaptureException ice) {
            //Do nothing
        }
        assertTrue(defaultBoard.getIsWhiteTurn());
    }

    @Test
    void isWhiteTurnGetterSetterTest() {
        assertTrue(defaultBoard.getIsWhiteTurn());
        defaultBoard.setIsWhiteTurn(false);
        assertFalse(defaultBoard.getIsWhiteTurn());
    }

    //Helpers
    //EFFECTS: Checks if all of the non-pawn pieces in this are in default positions
    private void assertDefaultOthers() {
        assert isContained(defaultBoard.getActive(), new Rook(0, 0, owner));
        assert isContained(defaultBoard.getActive(), new Knight(1, 0, owner));
        assert isContained(defaultBoard.getActive(), new Bishop(2,0, owner));
        assert isContained(defaultBoard.getActive(), new Queen(3, 0, owner));
        assert isContained(defaultBoard.getActive(), new King(4, 0, owner));
        assert isContained(defaultBoard.getActive(), new Bishop(5, 0, owner));
        assert isContained(defaultBoard.getActive(), new Knight(6, 0, owner));
        assert isContained(defaultBoard.getActive(), new Rook(7, 0, owner));
        assert isContained(defaultBoard.getActive(), new Rook(0, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Knight(1, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Bishop(2, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Queen(3, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new King(4, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Bishop(5, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Knight(6, 7, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Rook(7, 7, enemyOwner));
    }

    //EFFECTS: Checks if all of the pawn pieces in this are in default positions
    private void assertDefaultPawns() {
        assert isContained(defaultBoard.getActive(), new Pawn(0, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(1, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(2, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(3, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(4, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(5, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(6, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(7, 1, owner));
        assert isContained(defaultBoard.getActive(), new Pawn(0, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(1, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(2, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(3, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(4, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(5, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(6, 6, enemyOwner));
        assert isContained(defaultBoard.getActive(), new Pawn(7, 6, enemyOwner));
    }

    private void assertWhiteOwned() {
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(0, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(1, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(2, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(3, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(4, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(5, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(6, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Pawn(7, 1, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Rook(0, 0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Knight(1, 0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Bishop(2,0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Queen(3, 0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new King(4, 0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Bishop(5, 0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Knight(6, 0, owner));
        assert isContained(defaultBoard.getWhitePlayer().getOwned(), new Rook(7, 0, owner));
    }

    private void assertBlackOwned() {
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(0, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(1, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(2, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(3, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(4, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(5, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(6, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Pawn(7, 6, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Rook(0, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Knight(1, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Bishop(2, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Queen(3, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new King(4, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Bishop(5, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Knight(6, 7, enemyOwner));
        assert isContained(defaultBoard.getBlackPlayer().getOwned(), new Rook(7, 7, enemyOwner));
    }

    private boolean isContained(ArrayList<ChessPiece> active, ChessPiece p) {
        for (ChessPiece cp : active) {
            if (p.getPosY() == cp.getPosY() && p.getPosX() == cp.getPosX()) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
}
