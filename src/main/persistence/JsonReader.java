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
        JSONObject white = jsonObject.getJSONObject("White Player");
        JSONObject black = jsonObject.getJSONObject("Black Player");
        String enPassent = jsonObject.getString("En Passent Square");
        boolean isWhiteTurn = jsonObject.getBoolean("Is White's Turn");

        ChessGame cg = new ChessGame(parseUser(white), parseUser(black), enPassent, isWhiteTurn);
        cg.getWhitePlayer().setCurrentGame(cg);
        cg.getBlackPlayer().setCurrentGame(cg);

        swapUserCaptures(cg);

        return cg;
    }

    // EFFECTS: parses User from JSON object and returns it
    private User parseUser(JSONObject player) {
        String name = player.getString("Name");
        boolean isWhite = player.getBoolean("Is Playing White");
        User user = new User(isWhite, name);
        addPiecesToPlayer(player.getJSONArray("Owned Active Pieces"), user);
        addPiecesToCaptured(player.getJSONArray("Captured Enemy Pieces"), user);

        return user;
    }

    //EFFECTS: adds pieces from JSONArray to a users captured pieces
    private void addPiecesToCaptured(JSONArray captures, User user) {
        for (Object jsonObject : captures) {
            JSONObject nextPiece = (JSONObject) jsonObject;
            parseChessPiece(nextPiece, user);
            //user.getCaptured().add(parseChessPiece(nextPiece, user));
        }
    }

    //EFFECTS: adds pieces from JSONArray to a users active pieces
    private void addPiecesToPlayer(JSONArray active, User user) {
        user.getCaptured().clear();
        for (Object jsonObject : active) {
            JSONObject nextPiece = (JSONObject) jsonObject;
            parseChessPiece(nextPiece, user);
            //user.getOwned().add(parseChessPiece(nextPiece, user));
        }
    }


    //REQUIRES: symbol from JSON is one of P B N R Q K
    //EFFECTS: creates a chesspiece from JSON Object
    private ChessPiece parseChessPiece(JSONObject jsonObject, User user) {
        Translator t = new Translator();
        String symbol = jsonObject.getString("Piece Type");
        String location = jsonObject.getString("Piece Location");
        boolean captured = jsonObject.getBoolean("Is Captured");
        boolean moved = jsonObject.getBoolean("Has Moved");
        User player = user;

        if (symbol.equals("P")) {
            return new Pawn(t.translateToXCoord(location), t.translateToYCoord(location), player, captured, moved);
        } else if (symbol.equals("B")) {
            return new Bishop(t.translateToXCoord(location), t.translateToYCoord(location), player, captured, moved);
        } else if (symbol.equals("N")) {
            return new Knight(t.translateToXCoord(location), t.translateToYCoord(location), player, captured, moved);
        } else if (symbol.equals("R")) {
            return new Rook(t.translateToXCoord(location), t.translateToYCoord(location), player, captured, moved);
        } else if (symbol.equals("Q")) {
            return new Queen(t.translateToXCoord(location), t.translateToYCoord(location), player, captured, moved);
        } else if (symbol.equals("K")) {
            return new King(t.translateToXCoord(location), t.translateToYCoord(location), player, captured, moved);
        } else {
            return null;
        }
    }

    //EFFECTS: since captures are instantiated in the load as the wrong colour, this method
    //         swaps the owners of all of the captured pieces in chess game to the other player
    //         in chess game
    public void swapUserCaptures(ChessGame cg) {
        for (ChessPiece cp : cg.getWhitePlayer().getCaptured()) {
            cp.setOwner(cg.getBlackPlayer());
        }
        for (ChessPiece cp : cg.getBlackPlayer().getCaptured()) {
            cp.setOwner(cg.getWhitePlayer());
        }
    }
}
