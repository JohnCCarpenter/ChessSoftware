package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

    Bishop b1;
    Bishop b2;
    ChessPiece piece1;
    ChessPiece piece2;
    ArrayList<ChessPiece> active;
    User owner = new User();

    @BeforeEach
    public void setup() {
        b1 = new Bishop(0,0, owner);
        b2 = new Bishop(2, 6, owner);
    }

    @Test
    public void constructorTest() {
        assertEquals(0, b1.getPosX());
        assertEquals(0, b1.getPosY());
        assertEquals(2, b2.getPosX());
        assertEquals(6, b2.getPosY());
    }

    // Testing for the isLegalMove Method
    @Test
    public void isLegalMoveBlockedTest() {
        piece1 = new Pawn(1,1,owner);
        assertEquals(1, piece1.getPosX());
        assertEquals(1, piece1.getPosY());
        active = new ArrayList<ChessPiece>();
        active.add(piece1);
        assertFalse(b1.isLegalMove(active, 2,2));
    }

    @Test
    public void isLegalMoveOccupiedTest() {
        piece1 = new Pawn(2,2,owner);
        assertEquals(2, piece1.getPosX());
        assertEquals(2, piece1.getPosY());
        active = new ArrayList<ChessPiece>();
        active.add(piece1);
        assertFalse(b1.isLegalMove(active, 2,2));
    }

    @Test
    public void isLegalMoveBadMovementTest() {
        piece1 = new Pawn(7, 4, owner);
        assertEquals(7, piece1.getPosX());
        assertEquals(4, piece1.getPosY());
        active = new ArrayList<ChessPiece>();
        active.add(piece1);
        assertFalse(b1.isLegalMove(active, 3, 1));
    }

    @Test
    public void isLegalMoveSucceedTest() {
        piece1 = new Pawn(2,2,owner);
        assertEquals(2, piece1.getPosX());
        assertEquals(2, piece1.getPosY());
        active = new ArrayList<ChessPiece>();
        active.add(piece1);
        assertTrue(b1.isLegalMove(active, 1,1));
    }

    // Testing for the isLegalCapture Method
    @Test
    public void isLegalCaptureBlockedTest() {

    }

    @Test
    public void isLegalCaptureEmptyTest() {

    }

    @Test
    public void isLegalCaptureSucceedTest() {

    }
}
