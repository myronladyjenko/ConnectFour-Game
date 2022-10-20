package connectfour;

import java.util.Scanner;

public class TextUI {
    private Scanner scanner;
    private int userInputPosition;
    private final int menuOption = 0;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    public int getUserInputForIntegers(String messageForTheUser, int userInputOption) {
        setInputPosition(-1);

        do {
            printString(messageForTheUser);
            try {
                setInputPosition(scanner.nextInt());
            } catch (Exception e) {
                scanner.nextLine();
            }

            if (userInputOption == menuOption && !checkRangeForMenuOptions(getIntegerInput())) {
                setInputPosition(-1);
            }
        } while (getIntegerInput() == -1);
        //clear the input in case multiple values are inputed on the same line
        scanner.nextLine();

        return getIntegerInput();
    }

    private boolean checkRangeForMenuOptions(int userChoice) {
        if (userChoice < 1 || userChoice > 3) {
            return false;
        }
        return true;
    }

    public char getUserInputForCharacters(String messageForTheUser) {
        String str = "";
        char userChoice = '\0';
        do {
            printString(messageForTheUser);
            try {
                str += scanner.nextLine();

                if (str.length() == 2) {
                    str.replace("\n", "\0");
                } else if (str.length() == 1) {
                    userChoice = str.charAt(0);
                }
            } catch (Exception e) {
                // throw stuff here
            }
            userChoice = validateCharInput(userChoice);
        } while (userChoice == '\0');
        return userChoice;
    }

    private char validateCharInput(char userChoice) {
        if (userChoice == 'y' || userChoice == 'n') {
            return userChoice;
        }
        userChoice = '\0';
        return userChoice;
    }

    public void printString(String stringToPrint) {
        System.out.print(stringToPrint);
    }

    public void closeScanner() {
        scanner.close();
    }

    private void setInputPosition(int currentPosition) {
        userInputPosition = currentPosition;
    }

    private int getIntegerInput() {
        return userInputPosition;
    }
}