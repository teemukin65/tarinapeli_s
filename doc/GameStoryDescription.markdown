
# StoryGame


## Game initiation

1st player (GameInitiator) starts the game by giving list of e-mail addresses (in the playing order)
of the other players and perhaps a title and description
of the game for other players's invitation messages. 1st player also defines game ending criteria,
which could be:
   - number of turns of the each player for a story
   - Playing time
   - none --> until the 1st player so decides...

When all the game initiation information is provided, system provides or sends personal invitation links for
each player.

## Joining the game

When player opens the invitation link, he/she are provided view to optionally register / give nickname
for being able later return to all played games played by him/her.

## Game play

Then player gets the view to start the first story, by writing the  3 lines, and then indicating this part is complete.

After that player gets view for continuing a story that another player (previous in the playing order) has completed.
 In the story continuation view player sees the last line of the previous player, and fields for providing the 3
 following lines of the story.
 After the continuation lines are completed by the player he/she indicates that by pressing a completed - button
 And then player gets the next story to continue....

## Game end and aftermath

When the game ending criteria is fullfilled, or 1st player presses See the story - button, he get's list of stories
created by players. Each story list item shows if some player is still writing i.e. not submitted his/her last part.
 Other players are also forwarded to the story list after they finished the last story part.
The complete stories can be viewed via the story list.


## Information queries - access paths

Who has the turn to write in a story of the game?

Game --*> Story --> currentPlayer

What is the next writer for a story?

Story --> CurrentPlayer
Story --> Game - PlayingOrdering/lLayersGame -*> --> Player

What is my next hint line?

Player --*> Game  --> Story  --> CurrentPlayer
CurrentPlayer -PlayingOrdering-*> Game --*> Story  * story is selected according to some algorithm
Story --> Part  --> LastLine

