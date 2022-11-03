# Connect Four Game

The Connect Four game is a game that is played by two players. The game is played on the 6 by 7 grid. Players decide whether they play for 'X' or 'O'. Connect Four game is a game with "gravity". The token can only be placed in one of the 7 columns and it falls all the way to the bottom row or until it hits other player's token(piece). The game is played until 4 'X' or 'O' are found on the board (horizontally, vertically, or diagonally) in which case either 'X' or 'O' wins. If there are no possible moves left on the board the game is a tie.

## Description

The Connect Four game is a two-player game in which 'X' or 'O' is inputed by the user until someone wins or there are no possible moves.

When the game starts, welcome message in printed on the console and the user is prompted to enter one of the menu options:
* 1) Start a new game
* 2) Load a game from a file
* 3) Exit

**Note: When the board gets loaded, if the both users have same amount of tokens places, then user 'O' will go first.

Also, players are asked whether they want to turn on autosave (the file name is prompted once).

Once the user select the option, the program will print the current state of the board on the console and prompt the user to input a column to put his token in. After the user makes a move, the current state of the board is printed and the user is prompted to save the current state of the board to the file (if autosave option is specified, the board gets automatically saved). This process repeats until someone win or the game is a tie. If the file is saved, the user gets an option to quit the game by returning to the main menu and exiting.

My Connect Four game consists of 12 classes, 5 of which are user-defined exception classes. The classes are structured in way that Board class controls the state of the board and the board itself is build on BoardCell pieces, which represent each cell. The ConnectFour class is used to control the flow of the game based on the information that other classes could provided. TextUI class is used for interactions with the user(printing and getting the input). FileHandling, Player and Runner are suplementary classes the help with the building the game itself. For further description of the classes refer to the comment above each class in the source code.

## Getting Started

### Dependencies

To run the program the JDK 17 version or above should be installed. 
The other option to run the program is through gradle (this can be done inside the scioer container through the shell, through the extension or the local installation of gradle).

### Executing program

In order to build the project you can run command from the connectfour directory (package):
```
javaC Runner.java Board.java BoardCell.java ConnectFour.java FileHandling.java Player.java TextUI.java ThrowExceptionFileActionHasFailed.java ThrowExceptionForInvalidInput.java ThrowExceptionTheGameHasEnded.java ThrowExceptionWrongBoardFormat.java ThrowExceptionWrongMoveOnBoard.java
```

In otder to run the program, please run the command from the java directory:
```
java connectFour.Runner
```

## Limitations

The user is only allowed to save the file to the 'assets' folder.

## Author Information

Full Name: Myron Ladyjenko\
Email: mladyjen@uoguelph.ca\
Phone: +1 (343) 264-3588

## Development History

Major development steps:

* 0.8
    * Created an additional test, cleaned build.gradle file as well as fixed checkstyle errors in BoardTest class
    * See [b7c72fc0bbd59d7344f4c8234b14b8766cfe8927](Fixed last test + fixed checkstyle errors + removed 'test' task from build.gradle)
* 0.7
    * Created Junit tests from every method in Board class. Made adjustements to clean code in the classes
    * See [f36009f9dfd0888a71438ced6e7d539fdf2fc952](Minor adjustments to source code + created a Test Suite(JUnit Tests))
* 0.6
    * Added javadoc comments for public methods as well as comments explaining purpose of each class
    * See [1bd12c7f541d713dab6d35f3c77a647bb46461ef](Added all the Javadoc comments + created extra exception class)
* 0.5
    * Implemented file handling
    * See [9697b30aa7d39c39898cf80b07d60a4a76d4f9c7](Created FileHandling class)
* 0.4
    * Added exceptions + created fully functions user menu
    * See [795ce7368ce0af9195e9a78f0fdd4f69fe2bf05f](Deleted validateMove class, added exception for handling user input)
* 0.3
    * Resturctured creating of the board (only Cell class) + Added main functionaly to play the game + added all winConditions
    * See [240fadaf2908128271b14e9b6a12a46f5b1c4fe3](Created all win conditions + playGame functionality)
* 0.2
    * Created classes to create board + created Runner class
    * See [ef7002b1c32a7282e21550719700d45b413938f8](Created initial classes)
* 0.1
    * Initial Release

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)



