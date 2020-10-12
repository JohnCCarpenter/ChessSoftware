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

In the context of a chessboard application:
 - As a user, I want to be able to start a standard game of chess with the pieces where they are meant to be
 - As a user, I want to be able to move a piece from one position to another through a console
 - As a user, I want to be able to capture an enemy's piece with my own
 - As a user, I want to be able to see where the pieces are located on the board
 - As a user, I want to have the option to learn how different pieces are allowed to move in my game
 - As a user, I want to be able to see which pieces I have captured
 
 
 **An excerpt from the project requirements that needs to be met and explicitly stated above is as below:**
 
 *Note that in each of these sample applications, there is at least one user story that involves adding multiple Xs to 
 a Y (e.g. adding an item to a to-do list) where X and Y are classes that you must design yourself (so Y cannot be of
 type ArrayList<E> for example). By multiple, we mean that it will not be sufficient to always add a fixed X to a Y but
  this must be a dynamic process (i.e. in most cases this will mean there is no fixed size of Xs but this is not a 
  requirement). You must include at least one such user story in this phase of the application along with three others
  of your choosing.*
  
  Keeping track of captured pieces in a captures class could work
  Which pieces a user captured