package connectfour;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class BoardFileHandling {
    private boolean successfulLoadFromFile;
    
    public BoardFileHandling() {
        setStatusOfLoadOrSaveFromFile(false);
    }

    public void loadFile(String fileName, StringBuilder strToStoreBoard) 
                                  throws ThrowExceptionNoSuchFileExists {
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

    private void setStatusOfLoadOrSaveFromFile(boolean boolValue) {
        successfulLoadFromFile = boolValue;
    }

    public boolean getStatusOfLoadOrSaveFromFile() {
        return successfulLoadFromFile;
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

    public void saveToFile(String fileName, String stringToWriteToFile) throws ThrowExceptionNoSuchFileExists {
        fileName = fileName.replace("\n", "\0");
        checkFileExistsOtherwiseCreate("assets/", fileName);

        Path path = FileSystems.getDefault().getPath("assets/", fileName);
        try {
            Files.writeString(path, stringToWriteToFile);
        } catch(IOException e) {
            throw new ThrowExceptionNoSuchFileExists("Unable to write to the file: " + fileName);
        }
    }
}
