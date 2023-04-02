package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "CommandHistoryTest");

    @Test
    public void commandHistoryConstructor_fileDoesNotExist_fileCreated() {
        Path dummyFilePath = TEST_DATA_FOLDER.resolve("dummyFile.txt");
        File dummyFile = new File(dummyFilePath.toString());

        assertFalse(dummyFile.exists());
        CommandHistory commandHistory = new CommandHistory(dummyFilePath);
        assertTrue(dummyFile.exists());

        // clean up
        assertTrue(dummyFile.delete());
    }

    @Test
    public void commandHistoryConstructor_fileAlreadyExists_success() {
        Path filePath = TEST_DATA_FOLDER.resolve("command_history.txt");
        File file = new File(filePath.toString());

        assertTrue(file.exists());
        CommandHistory commandHistory = new CommandHistory(filePath);
        assertTrue(file.exists());
    }

    @Test
    public void navigateCommandHistory_fileAlreadyExists_success() {
        Path filePath = TEST_DATA_FOLDER.resolve("command_history.txt");
        File file = new File(filePath.toString());

        assertTrue(file.exists());
        CommandHistory commandHistory = new CommandHistory(filePath);
        assertTrue(file.exists());

        String originalString = "original";
        assertEquals(commandHistory.getPreviousUserInput(originalString), "list 3");
        assertEquals(commandHistory.getPreviousUserInput("dummy aaa"), "clear");
        assertEquals(commandHistory.getPreviousUserInput("dummy bbb"), "list 2");
        assertEquals(commandHistory.getPreviousUserInput("dummy ccc"), "list 1");
        assertNull(commandHistory.getPreviousUserInput("dummy dddd"));

        assertEquals(commandHistory.getNextUserInput(), "list 2");
        assertEquals(commandHistory.getNextUserInput(), "clear");
        assertEquals(commandHistory.getNextUserInput(), "list 3");
        assertEquals(commandHistory.getNextUserInput(), originalString);
        assertNull(commandHistory.getNextUserInput());
    }

    @Test
    public void commitUserInputAndUpdateCommandHistoryFile_fileDoesNotExist_success() {
        Path testFilePath = TEST_DATA_FOLDER.resolve("testFile.txt");
        File testFile = new File(testFilePath.toString());

        assertFalse(testFile.exists());
        CommandHistory commandHistory = new CommandHistory(testFilePath);
        assertTrue(testFile.exists());

        commandHistory.commitUserInput("first");
        commandHistory.commitUserInput("second");
        commandHistory.commitUserInput("third");

        try {
            String fileContents = Files.readString(testFilePath);
            assertEquals("first\nsecond\nthird", fileContents);
        } catch (IOException e) {
            // hopefully this doesn't happen during testing
            throw new RuntimeException(e);
        }

        // clean up
        assertTrue(testFile.delete());
    }

    @Test
    public void commitUserInputAndUpdateCommandHistoryFile_fileAlreadyExists_success() {
        Path filePath = TEST_DATA_FOLDER.resolve("command_history.txt");
        Path filePathCopy = TEST_DATA_FOLDER.resolve("command_history_copy.txt");

        try {
            Files.copy(filePath, filePathCopy);
        } catch (IOException e) {
            // hopefully this doesn't happen during testing
            throw new RuntimeException(e);
        }

        File fileCopy = new File(filePathCopy.toString());

        assertTrue(fileCopy.exists());
        CommandHistory commandHistory = new CommandHistory(filePathCopy);
        assertTrue(fileCopy.exists());

        commandHistory.commitUserInput("first");
        commandHistory.commitUserInput("second");
        commandHistory.commitUserInput("third");

        try {
            String fileCopyContents = Files.readString(filePathCopy);
            assertEquals("list 1\nlist 2\nclear\nlist 3\nfirst\nsecond\nthird", fileCopyContents);
        } catch (IOException e) {
            // hopefully this doesn't happen during testing
            throw new RuntimeException(e);
        }

        // clean up
        assertTrue(fileCopy.delete());
    }
}
