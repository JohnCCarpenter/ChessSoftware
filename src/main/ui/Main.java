package ui;

import model.ChessGame;
import model.ChessPiece;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static ui.TextConsole.DEFAULT_FILE;

public class Main {
    public static void main(String[] args) {
        Scanner input;
        String action;
        TextConsole instance;
        String whiteOwner;
        String blackOwner;
        ArrayList<ChessPiece> loadedPieces;
        ChessGame loadedGame;
        JsonReader reader;

        input = new Scanner(System.in);
        System.out.println("Do you want to load last game or create new? (Type new or load)");
        action = input.next();
        action.toLowerCase();
        if (action.equals("new")) {
            instance = new TextConsole();
        } else if (action.equals("load")) {
            reader = new JsonReader(DEFAULT_FILE);
            try {
                loadedGame = reader.read();
            } catch (IOException ioe) {
                System.out.println("Save file read unsuccessful");
                loadedGame = null;
            }
            instance = new TextConsole(loadedGame);
        }

    }
}
