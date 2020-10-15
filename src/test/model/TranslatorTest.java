package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TranslatorTest {
    Translator t;

    @BeforeEach
    public void setup() {
        t = new Translator();
    }

    @Test
    public void translateToXCoordATest() {
        assertEquals(0, t.translateToXCoord("a7"));
    }

    @Test
    public void translateToXCoordBTest() {
        assertEquals(1, t.translateToXCoord("b1"));
    }

    @Test
    public void translateToXCoordCTest() {
        assertEquals(2, t.translateToXCoord("c4"));
    }

    @Test
    public void translateToXCoordDTest() {
        assertEquals(3, t.translateToXCoord("d5"));
    }

    @Test
    public void translateToXCoordETest() {
        assertEquals(4, t.translateToXCoord("e8"));
    }

    @Test
    public void translateToXCoordFTest() {
        assertEquals(5, t.translateToXCoord("f3"));
    }

    @Test
    public void translateToXCoordGTest() {
        assertEquals(6, t.translateToXCoord("g6"));
    }

    @Test
    public void translateToXCoordHTest() {
        assertEquals(7, t.translateToXCoord("h2"));
    }

    @Test
    public void translateToYCoordLowTest() {
        assertEquals(0, t.translateToYCoord("a1"));
    }

    @Test
    public void translateToYCoordHighTest() {
        assertEquals(7, t.translateToYCoord("b8"));
    }

    @Test
    public void translateToYCoordTest() {
        assertEquals(4, t.translateToYCoord("h5"));
    }

    @Test
    public void translateToChessCoordATest() {
        assertEquals("a7", t.translateToChessCoord(0, 6));
    }

    @Test
    public void translateToChessCoordBTest() {
        assertEquals("b1", t.translateToChessCoord(1, 0));
    }

    @Test
    public void translateToChessCoordCTest() {
        assertEquals("c4", t.translateToChessCoord(2, 3));
    }

    @Test
    public void translateToChessCoordDTest() {
        assertEquals("d5", t.translateToChessCoord(3, 4));
    }

    @Test
    public void translateToChessCoordETest() {
        assertEquals("e8", t.translateToChessCoord(4, 7));
    }

    @Test
    public void translateToChessCoordFTest() {
        assertEquals("f3", t.translateToChessCoord(5, 2));
    }

    @Test
    public void translateToChessCoordGTest() {
        assertEquals("g6", t.translateToChessCoord(6, 5));
    }

    @Test
    public void translateToChessCoordHTest() {
        assertEquals("h2", t.translateToChessCoord(7, 1));
    }
}
