package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//THIS CLASS WAS based off of a class in JsonSerializationDemo from CPSC 210
class JsonReaderTest {
    ChessGame baseGame;
    ChessGame customGame;
    User owner;
    User enemyOwner;
    ChessPiece bpe4;
    ChessPiece wkd4;
    ChessPiece bba8;
    ChessPiece wqa1;

    @BeforeEach
    void setup() {
        owner = new User(true, "John");
        enemyOwner = new User(false, "Buddy");

        bpe4 = new Pawn(4,3, enemyOwner);
        bba8 = new Bishop(0, 7, enemyOwner);
        wkd4 = new King(3, 3, owner);
        wqa1 = new Queen(0, 0, owner);

        owner.getOwned().add(wkd4);
        owner.getOwned().add(wqa1);
        enemyOwner.getOwned().add(bpe4);
        enemyOwner.getOwned().add(bba8);

        customGame = new ChessGame(owner, enemyOwner,"e3", false);
        baseGame = new ChessGame("Boring", "Yuckers");
    }


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ChessGame chessGame = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderBaseChessGame() {
        JsonReader reader = new JsonReader("./data/testReaderBaseChessGame.json");
        try {
            ChessGame testGame = reader.read();
            assertEquals(baseGame, testGame);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCustomChessGame() {
        JsonReader reader = new JsonReader("./data/testReaderCustomChessGame.json");
        try {
            ChessGame testGame = reader.read();
            assertEquals(customGame, testGame);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSwapUserCapturesBothEmpty() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        reader.swapUserCaptures(customGame);

        assertEquals(owner, wkd4.getOwner());
        assertEquals(owner, wkd4.getOwner());
        assertEquals(enemyOwner, bba8.getOwner());
        assertEquals(enemyOwner, bpe4.getOwner());

    }

    @Test
    void testSwapUserCapturesWhiteEmpty() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        enemyOwner.getCaptured().add(bba8);
        enemyOwner.getCaptured().add(bpe4);

        reader.swapUserCaptures(customGame);

        assertEquals(owner, wkd4.getOwner());
        assertEquals(owner, wkd4.getOwner());
        assertEquals(owner, bba8.getOwner());
        assertEquals(owner, bpe4.getOwner());
    }

    @Test
    void testSwapUserCapturesBlackEmpty() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        owner.getCaptured().add(wqa1);
        owner.getCaptured().add(wkd4);

        reader.swapUserCaptures(customGame);

        assertEquals(enemyOwner, wkd4.getOwner());
        assertEquals(enemyOwner, wkd4.getOwner());
        assertEquals(enemyOwner, bba8.getOwner());
        assertEquals(enemyOwner, bpe4.getOwner());
    }

    @Test
    void testSwapUserCapturesBothOccupied() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        owner.getCaptured().add(wqa1);
        owner.getCaptured().add(wkd4);
        enemyOwner.getCaptured().add(bpe4);
        enemyOwner.getCaptured().add(bba8);

        reader.swapUserCaptures(customGame);

        assertEquals(enemyOwner, wkd4.getOwner());
        assertEquals(enemyOwner, wkd4.getOwner());
        assertEquals(owner, bba8.getOwner());
        assertEquals(owner, bpe4.getOwner());
    }
}
