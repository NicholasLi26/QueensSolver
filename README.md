# Intro
QueensSolver is a logic puzzle solver for the LinkedIn game Queens written in Java Using Swing componenets for a rudimentary GUI. It was made as a fun side project that also helped me further understand the logic behind the game. 

## How it Works

QueensSolver utilizes written logic checks similar to how one would solve the game manually. It currently progresses through these checks linearly from quickest to longest.

Currently up to a 10x10 puzzle can be inputted, but larger puzzles will soon be implemented.

Main problem solving functions are found in the Queens.java file, while other files handle the window and swing component creation. 

## Using the Program

Upon execution:

1. Select size
2. select a colour and input where said colour should be by clicking on the box
3. Overwriting or going back a colour can be done before all squares are inputted
4. Once array is complete, click compute
5. Output will be displayed on command line


# Notes
It is still a work in progress, with new features still being added. Current list of additions to be added:
1. Overlayed Queens placements on main window
2. Checks for surrounding X's for colour arrays >3 in size
3. improved GUI
