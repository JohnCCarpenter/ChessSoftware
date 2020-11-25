package ui;

import model.ChessGame;
import model.ChessPiece;
import persistence.JsonReader;
import ui.gui.VisualConsole;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static ui.TextConsole.DEFAULT_FILE;

public class Main {
    private static Scanner input;
    private String action;
    static String whiteOwner;
    static String blackOwner;

    public static void main(String[] args) {

        input = new Scanner(System.in);

        System.out.println("Who is playing with the white pieces?");
        whiteOwner = input.next();
        System.out.println("Who is playing with the black pieces?");
        blackOwner = input.next();

        visualConsoleQuery(input);

    }

    private static void visualConsoleQuery(Scanner input) {
        System.out.println("Visual or Console?");
        if (input.next().toLowerCase().equals("visual")) {
            new VisualConsole(whiteOwner, blackOwner);
        } else if (input.next().toLowerCase().equals("console")) {
            new TextConsole(whiteOwner, blackOwner);
        } else {
            visualConsoleQuery(input);
        }
    }
}
