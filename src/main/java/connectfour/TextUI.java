package connectfour;

import java.util.Scanner;

/**
 * This class represent a way to interact with the user. The purpose of this class 
 * is to print everything for the user and take user input.
 * 
 * @author Myron Ladyjenko
 */
public class TextUI {
    private Scanner scanner;
    private ConnectFour connectFour;
    private int userInputInteger;
    private char userInputCharacter;
    private String userFileNameInput;

    /**
     * This is an empty constructor used to initialize private members of 
     * this class to invalid values
     */
    public TextUI() {
        scanner = new Scanner(System.in);
        setCharacterInput("\0");
        setFileNameInput("invalidName");
        setIntegerInput("-1");
    }

    /**
     * It takes a message to print to the user and a type of input to check for, and then the method loops
     * until the user enters valid input
     * 
     * @param messageForTheUser The message that will be displayed to the user.
     * @param typeToCheckFor This is used to detect which type of input we are looking for
     */
    public void getUserInput(String messageForTheUser, int typeToCheckFor) {
        do {
            printString(messageForTheUser);
            try {
                String userInputString = scanner.nextLine();

                handleExceptions(typeToCheckFor, userInputString);
                break;
            } catch (ThrowExceptionForInvalidInput incorrectInputEx) {
                printString(incorrectInputEx.getMessage() + "\n");
            } catch (ThrowExceptionWrongMoveOnBoard incorrectMoveEx) {
                printString(incorrectMoveEx.getMessage() + "\n");
            } catch (ThrowExceptionFileActionHasFailed incorrectFileName) {
                printString(incorrectFileName.getMessage() + "\n");
            }
            printString("\n");
        } while (true);
    }

    private void handleExceptions(int inputTypeToCheckFor, String inputString) 
            throws ThrowExceptionForInvalidInput, ThrowExceptionWrongMoveOnBoard, ThrowExceptionFileActionHasFailed {
        if (inputTypeToCheckFor == connectFour.getConstCharacter()) {
            connectFour.validateMove(inputTypeToCheckFor, inputString);
            setCharacterInput(inputString);
        } else if (inputTypeToCheckFor == connectFour.getConstInteger()) {
            connectFour.validateMove(inputTypeToCheckFor, inputString);
            setIntegerInput(inputString);
        } else if (inputTypeToCheckFor == connectFour.getConstBoardPosition()) {
            connectFour.validateMove(inputTypeToCheckFor, inputString);
            setIntegerInput(inputString);
        } else if (inputTypeToCheckFor == connectFour.getConstFileNameLoad()) {
            connectFour.validateMove(inputTypeToCheckFor, inputString);
            setFileNameInput(inputString);
        } else if (inputTypeToCheckFor == connectFour.getConstFileNameSave()) {
            setFileNameInput(inputString);
            connectFour.validateMove(inputTypeToCheckFor, inputString);
        } 
    }

    /**
     * This function takes a string as an argument and prints it to the console.
     * 
     * @param stringToPrint The string to print to the console.
     */
    public void printString(String stringToPrint) {
        System.out.print(stringToPrint);
    }

    /**
     * Close the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    private void setIntegerInput(String userInput) {
        userInputInteger = Integer.parseInt(userInput.toString());
    }

    private void setCharacterInput(String userInput) {
        userInputCharacter = userInput.charAt(0);
    }

    private void setFileNameInput(String userInput) {
        userFileNameInput = userInput;
    }

    /**
     * This function returns the integer value of the user input
     * 
     * @return The userInputInteger variable is being returned.
     */
    public int getIntegerInput() {
        return userInputInteger;
    }

    /**
     * This function returns the character that the user inputted.
     * 
     * @return The char userInputCharacter variable is being returned.
     */
    public char getCharacterInput() {
        return userInputCharacter;
    }
    
    /**
     * This function returns the user's input for the file name
     * 
     * @return The String userFileNameInput is being returned.
     */
    public String getFileNameInput() {
        return userFileNameInput;
    }

    /**
     * This function sets the private variable of type connectFour of this class 
     * to the game (instance of connectFour) that is passed in.
     * 
     * @param game The game object that is being played (instance of ConnectFour created in main).
     */
    public void setGame(ConnectFour game) {
        connectFour = game;
    }

    /**
     * The toString() function is a function that returns a string
     * 
     * @return A string.
     */
    public String toString() {
        return "I am a TextUI and I am here to connect to the user.\n";
    }
}