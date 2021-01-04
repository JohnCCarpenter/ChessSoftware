# Chess Software : Created by John Carpenter for CPSC 210 Course at UBC

## Design Goals


For my personal project in *CPSC 210: Software Design* I will be creating a **virtual chess board**.
To start the project will simply have a text based approach where the players will input their desired
move and need to visualize the board. This could later be used as a secondary device to train blindfolded
chess! Later I will implement a graphical interface which includes the board, pieces as well as some
useful stats like how many pieces have been captured for each side. 

**Extra functionality in the future could include:**
 - an area to study openings
 - an area to solve puzzles/tactics
 - an area to store previous games for analysis
 - imports of chess computers for help in analysis
 - addition of AI opponents

## Targeted Audience
This project will be able to be used for anyone who wants a local based chess app on their computer. In
the future it would be amazing to deploy this app to a website to allow for people to play with each other
online, but that will not be covered within the scope of this project. 

## Motivation

I have recently become quite enamored by the game of chess. I had always had some interest in the game
but never really committed to learning many of the specifics of the game. Recently I have been playing
many games and watching different chess streamers and youtubers like IM Levy Rosman (GothamChess), 
IM Eric Rosen, and GM Hikaru Nakamura. Starting out at around 800 rating on Chess.com I have reached up
to 1100 in blitz and 1300 in rapid. Hopefully through programming this app I will be able to foster 
some of this newfound interest in the game while improving my own chess game through the visualization 
that I will need to use while designing this board.

## User Stories

In the context of a chessboard application, as a user, I want to be able to:
 - start a standard game of chess with the pieces where they are meant to be
 - move a piece from one position to another through a console
 - capture an enemy's piece with my own
 - see where the pieces are located on the board
 - see which pieces I have captured
 - save a game's position to continue later (after entering quit)
 - load a previously saved game based on who was playing it
 - control the pieces in the game through a graphical user interface
 - see the pieces that I have captured displayed, as well as how many points up I am
 
 As of this update, the preceding user stories have been implemented. In the future I would love to include the 
 following functionality but due to the time constraints I have been unable to. For this reason this board functions
 well for two experienced players but may allow some illegal moves.
  - put a king in check
  - end the game by putting my opponents king in checkmate
  - castle the king 
 - See the order of moves that happened during a game (using algebraic chess notation)
 - Using the above, have an openings explorer where I could see the move orders of common openings to look through
 - Have a tactics testing option, where you are given a position and need to find the best series of moves
 
## Phase 4: Task 2 JAVA CONSTRUCTS
Due to the complex nature of this application I have already implemented multiple of the Java language constructs into
my project. I have methods that throw exceptions when strange behaviour is presented and this functionality is tested 
(the IllegalCaptureException and IllegalMovementException heavily used in the movement of ChessPieces for example). I
have implemented a subclass type hierarchy to represent the individual types of ChessPieces and their unique movement
and properties while keeping the majority of their functionality general. This accounts for 6 subclasses each with 
distinct functionality by overriding and adding methods. I also have a bi directional association between the user class
and the ChessGame class to allow for ChessPieces to check the state of the game so that movement options like En Passent
, Castling, and Check restrictions can be enforced. There are also a huge number of instances where a class needs to 
call a method from another class.

## Phase 4: Task 3 REFACTORING OPTIONS
If you look at the class diagram you may notice that there is a high degree of coupling between the ChessGame,
ChessPiece, and User classes that could be fixed with some refactoring. This would be to remove the connection between 
ChessGame and ChessPiece and simply access that information through the User who owns the pieces. Additionally, instead
of having Visual Console and ChessBoard have info about their shared current ChessGame, I could just store that info in
the VisualConsole and access it for the ChessBoard through its connection there. 
Other than those adjustments the coupling is quite reasonable considering the complexity of the program.
Overall I am quite happy with the cohesion in my classes, all the actions related to chess pieces are in the Chess Piece
folder and it is similar for all the classes in my model class. In my gui section there are some adjustments I would 
make though, including creating tools to deal with images (for example I have a common method in two of the classes that
just scales an image). I would also take out the individual wp1 wp2 etc. fields in chessgame as a simple list would be 
sufficient and would dramatically improve readability. The same goes for the whiteplayer and blackplayer
fields in that class.
