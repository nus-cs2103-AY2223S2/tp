package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UploadCommand.
 */
class UploadCommandTest {

    private static final String TEST_FILE_PATH = "src/test/data/UploadFileTest/test.txt";
    private static final String DUPLICATE_FILE_PATH = "src/test/data/UploadFileTest/DuplicateFile/test.txt";
    private static final String INVALID_FILE_PATH = "invalid/file/path.txt";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPathMessage_success() {
        Path sourcePath = Paths.get(TEST_FILE_PATH);
        UploadCommand uploadCommand = new UploadCommand(sourcePath);
        String expectedMessage = "File " + sourcePath.getFileName() + " successfully added to CLIpboard";
        assertCommandSuccess(uploadCommand, model, expectedMessage, model);

        //delete test.txt from data folder
        File toDelete = new File(UploadCommand.DESTINATION_FILEPATH + "/test.txt");
        toDelete.delete();
    }

    @Test
    public void execute_validPathUpload_success() {
        Path sourcePath = Paths.get(TEST_FILE_PATH);
        UploadCommand uploadCommand = new UploadCommand(sourcePath);
        try {
            uploadCommand.execute(model);
            Path destinationPath = Paths.get(UploadCommand.DESTINATION_FILEPATH).resolve(sourcePath.getFileName());
            assertTrue(Files.exists(destinationPath));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        //delete test.txt from data folder
        File toDelete = new File(UploadCommand.DESTINATION_FILEPATH + "/test.txt");
        toDelete.delete();
    }

    @Test
    public void execute_duplicateFile_success() {
        UploadCommand uploadTestCommand = new UploadCommand(Paths.get(TEST_FILE_PATH));
        UploadCommand uploadDuplicateCommand = new UploadCommand(Paths.get(DUPLICATE_FILE_PATH));
        try {
            uploadTestCommand.execute(model);
            uploadDuplicateCommand.execute(model);
            String sourceText = new String(Files.readAllBytes(Paths.get(DUPLICATE_FILE_PATH)));
            String destinationText =
                    new String(Files.readAllBytes(Paths.get(UploadCommand.DESTINATION_FILEPATH + "/test.txt")));
            assertTrue(destinationText.equals(sourceText));
        } catch (CommandException | IOException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }

        //delete test.txt from data folder
        File toDelete = new File(UploadCommand.DESTINATION_FILEPATH + "/test.txt");
        toDelete.delete();
    }

    @Test
    public void execute_invalidPath_throwsCommandException() {
        Path sourcePath = Paths.get(INVALID_FILE_PATH);
        UploadCommand uploadCommand = new UploadCommand(sourcePath);
        assertCommandFailure(uploadCommand, model, UploadCommand.MESSAGE_INVALID_FILEPATH);

    }

    @Test
    void equals() {
        UploadCommand uploadFirstCommand = new UploadCommand(Paths.get(TEST_FILE_PATH));
        UploadCommand uploadSecondCommand = new UploadCommand(Paths.get(DUPLICATE_FILE_PATH));

        // same object -> returns true
        assertTrue(uploadFirstCommand.equals(uploadFirstCommand));

        // same values -> returns true
        UploadCommand uploadFirstCommandCopy = new UploadCommand(Paths.get(TEST_FILE_PATH));
        assertTrue(uploadFirstCommand.equals(uploadFirstCommandCopy));

        // different types -> returns false
        assertFalse(uploadFirstCommand.equals(1));

        // null -> returns false
        assertFalse(uploadFirstCommand.equals(null));

        // different file -> returns false
        assertFalse(uploadFirstCommand.equals(uploadSecondCommand));
    }
}
