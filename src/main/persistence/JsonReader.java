package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Taken and adjusted for this projects needs from JsonSerializationDemo.java file on class github
// Represents a reader that reads and creates ChessGame from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ChessGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChessGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ChessGame from JSON object and returns it
    private ChessGame parseChessGame(JSONObject jsonObject) {
        String white = jsonObject.getString("White Player");
        String black = jsonObject.getString("Black Player");
        ChessGame cg = new ChessGame(white, black);
        cg.getActive().clear();

        JSONArray activePiecesJson = jsonObject.getJSONArray("Active Pieces");
        addPieces(activePiecesJson, cg);

        return cg;
    }

    //EFFECTS: adds chess pieces from jsonArray to cg
    private void addPieces(JSONArray jsonArray, ChessGame cg) {
        ArrayList<ChessPiece> list = new ArrayList<ChessPiece>();
        for (Object jsonObject : jsonArray) {
            JSONObject nextPiece = (JSONObject) jsonObject;
            cg.getActive().add(parseChessPiece(nextPiece, cg));
        }
    }

    //REQUIRES: symbol from JSON is one of P B N R Q K
    //EFFECTS: creates a chesspiece from JSON Object
    private ChessPiece parseChessPiece(JSONObject jsonObject, ChessGame cg) {
        Translator t = new Translator();
        String symbol = jsonObject.getString("Piece Type");
        String owner = jsonObject.getString("Piece Owner");
        String location = jsonObject.getString("Piece Location");
        User player = assignOwner(cg, owner);

        if (symbol == "P") {
            return new Pawn(t.translateToXCoord(location), t.translateToYCoord(location), player);
        } else if (symbol == "B") {
            return new Bishop(t.translateToXCoord(location), t.translateToYCoord(location), player);
        } else if (symbol == "N") {
            return new Knight(t.translateToXCoord(location), t.translateToYCoord(location), player);
        } else if (symbol == "R") {
            return new Rook(t.translateToXCoord(location), t.translateToYCoord(location), player);
        } else if (symbol == "Q") {
            return new Queen(t.translateToXCoord(location), t.translateToYCoord(location), player);
        } else if (symbol == "K") {
            return new King(t.translateToXCoord(location), t.translateToYCoord(location), player);
        } else {
            return null;
        }
    }

    private User assignOwner(ChessGame cg, String owner) {
        if (owner == cg.getWhitePlayer().getName()) {
            return cg.getWhitePlayer();
        } else if (owner == cg.getBlackPlayer().getName()) {
            return cg.getBlackPlayer();
        } else {
            return null;
        }
    }
}
