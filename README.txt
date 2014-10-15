

Team Members :
Mohammad Hashim Raza                                   651291
Zhanibek Kozhirbayev                                   598577
Sreenath Thozhukkattu Vadakkeveettil                   646823


"A Cooperative Breakout Game" 


StartPage - This is the main Activity class for BreakBrick. The user can choose to start a
new game or resume a saved game. The user also can change levels by downloading from the server. 
There is a high score table and help content as well. 

MainActivity - Activity for the running game. Holds the game's graphics thread. Saves and
restores game data when paused or resumed.

WorlView - Creates and draws the graphics for the game. Runs a Thread for animation and game 
physics. Saves and restores game data when paused or restored.


Ball - Represents a game ball. Responsible for drawing the ball, updating the ball's position, collision checking.
 
Block - Represents a single game block object. Extends a ShapeDrawable to include a Color value 
and a method for exporting the coordinates and color in order to save its state.

Paddle - Represents the game paddle. Extends a ShapeDrawable to include a color value and methods 
for setting the paddle's location on screen.

HelpDialog - PopUp dialoge to show help content

HighScore - Activity for representing table of scores read from the server

LoadGameLevel - Activity for changing levels

PrompDialogue - PopUp dialoge to put user's name when there is high score


Mohammad Hashim Raza (Paddle, CouchDB, LevelChange)
Zhanibek Kozhirbayev (Block, CouchDB, Interface)
Sreenath Thozhukkattu Vadakkeveettil (Ball, Physics, Collisions)
