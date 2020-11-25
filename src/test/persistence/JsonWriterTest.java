package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import model.*;


import static org.junit.jupiter.api.Assertions.*;

//THIS CLASS WAS based off of a class in JsonSerializationDemo from CPSC 210
class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
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

        customGame = new ChessGame(owner, enemyOwner,"e3", false);
        baseGame = new ChessGame("Boring", "Yuckers");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterBaseChessGame() {
        try {
            JsonWriter writer = new JsonWriter("./data/testReaderBaseChessGame.json");
            writer.open();
            writer.write(baseGame);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBaseChessGame.json");
            ChessGame readGame = reader.read();
            assertEquals(baseGame, readGame);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralChessGame() {
        try {
            enemyOwner.getCaptured().add(wqa1);
            owner.getOwned().remove(wqa1);
            wqa1.setCaptured(true);
            owner.getCaptured().add(bpe4);
            enemyOwner.getOwned().remove(bpe4);
            customGame.getActive().remove(bpe4);
            customGame.getActive().remove(wqa1);
            bpe4.setCaptured(true);

            JsonWriter writer = new JsonWriter("./data/testReaderCustomChessGame.json");
            writer.open();
            writer.write(customGame);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCustomChessGame.json");
            ChessGame readGame = reader.read();
            assertEquals(customGame, readGame);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

