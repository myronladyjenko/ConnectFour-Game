package connectfour;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is used as a helper class that is used for File Handling. The purpose of this class is to
 * load from file and save to file. In both cases we are dealing with the string representation of the board, 
 * however, the class doesn't know that the string in any way relates to the board.
 * 
 * @author Myron Ladyjenko
 */
public class FileHandling {
    private boolean successfulLoadFromFile;
    private StringBuilder strToStoreBoard;
    
    public FileHandling() {
        setStatusOfLoadOrSaveFromFile(false);
        strToStoreBoard = new StringBuilder("");
    }

    /**
     * It takes a file name and a StringBuilder object as parameters, and it reads the file line by
     * line and appends each line to the StringBuilder object
     * 
     * @param fileName The name of the file to be loaded.
     * @param strToStoreBoard is a StringBuilder object that will store the contents of the file.
     * It will sotre the string representation of the board (read from file).
     */
    public void loadFile(String fileName) throws ThrowExceptionNoSuchFileExists {
        String oneLine = "";
        BufferedReader myReader;
        fileName = fileName.replace("\n", "\0");
        Path path = FileSystems.getDefault().getPath("assets/", fileName);
        try {
            myReader = Files.newBufferedReader(path);
            while ((oneLine = myReader.readLine()) != null) {
                strToStoreBoard.append(oneLine);
            }
            myReader.close();
            setStatusOfLoadOrSaveFromFile(true);
        } catch (Exception e) {
            throw new ThrowExceptionNoSuchFileExists("Provided file name is incorrect or doesn't exist: " + fileName);
        }
    }

    /**
     * This function takes a file name and a string to write to the file, and writes the string to the
     * file.
     * 
     * @param fileName The name of the file to write to.
     * @param stringToWriteToFile The string (string representation of board) that you want to write to the file.
     */
    public void saveToFile(String fileName, String stringToWriteToFile) throws ThrowExceptionNoSuchFileExists {
        fileName = fileName.replace("\n", "\0");
        checkFileExistsOtherwiseCreate("assets/", fileName);

        Path path = FileSystems.getDefault().getPath("assets/", fileName);
        try {
            Files.writeString(path, stringToWriteToFile);
            setStatusOfLoadOrSaveFromFile(true);
        } catch(IOException e) {
            throw new ThrowExceptionNoSuchFileExists("Unable to write to the file: " + fileName);
        }
    }

    private void checkFileExistsOtherwiseCreate(String fileLocationPrefix, String fileName) 
                                                throws ThrowExceptionNoSuchFileExists {
        // construct a file
        File file = new File(fileLocationPrefix + fileName);

        if (!file.exists()) {
            try {
                file.createNewFile(); 
            } catch (Exception ex) {
                throw new ThrowExceptionNoSuchFileExists("Couldn't open the file " + fileName);
            }
        }
    }

    private void setStatusOfLoadOrSaveFromFile(boolean boolValue) {
        successfulLoadFromFile = boolValue;
    }

    /**
     * This function returns the status of the load or save from file operation
     * 
     * @return The status of the load from file.
     */
    public boolean getStatusOfLoadOrSaveFromFile() {
        return successfulLoadFromFile;
    }

    public String toString() {
        return strToStoreBoard.toString();
    }
}
