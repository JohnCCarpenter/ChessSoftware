# JCChess : Created by John Carpenter for CPSC 210 Course at UBC

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
 - ~~start a standard game of chess with the pieces where they are meant to be~~
 - ~~move a piece from one position to another through a console~~
 - ~~capture an enemy's piece with my own~~
 - ~~see where the pieces are located on the board~~
 - ~~see which pieces I have captured~~
 - learn how different pieces are allowed to move in my game
 - put my opponents king in check
 - end the game by putting my opponents king in checkmate

 - ~~save a game's position to continue later (after entering quit)~~
 - ~~load a previously saved game based on who was playing it~~
 
 - save a game's move order to review later
 - see my record under a particular username
 
 - control the pieces in the game through a graphical user interface
 

  
  Keeping track of captured pieces in a captures class could work
  Which pieces a user captured