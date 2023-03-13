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

    private static final Path UPLOAD_TEST_FOLDER_PATH = Paths.get("src/test/data/UploadFileTest");

    private static final Path SOURCE_FOLDER_PATH = UPLOAD_TEST_FOLDER_PATH.resolve("SourceFolder");
    private static final Path DESTINATION_FOLDER_PATH = UPLOAD_TEST_FOLDER_PATH.resolve("DestinationFolder");

    private static final Path SOURCE_TEST_FILE = SOURCE_FOLDER_PATH.resolve("test.txt");
    private static final Path SOURCE_DUPLICATE_FILE = SOURCE_FOLDER_PATH.resolve("DuplicateFile/test.txt");

    private static final Path INVALID_FILE_PATH = Paths.get("invalid/file/path.txt");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPathMessage_success() {
        Path sourcePath = SOURCE_TEST_FILE;
        Path destPath = DESTINATION_FOLDER_PATH;
        UploadCommand uploadCommand = new UploadCommand(sourcePath, destPath);
        String expectedMessage = "File " + sourcePath.getFileName() + " successfully added to CLIpboard";
        assertCommandSuccess(uploadCommand, model, expectedMessage, model);

        //delete test.txt from destination folder
        File toDelete = new File(DESTINATION_FOLDER_PATH + "/test.txt");
        toDelete.delete();
    }

    @Test
    public void execute_validPathUpload_success() {
        Path sourcePath = SOURCE_TEST_FILE;
        Path destPath = DESTINATION_FOLDER_PATH;
        UploadCommand uploadCommand = new UploadCommand(sourcePath, destPath);
        try {
            uploadCommand.execute(model);
            Path destinationPath = destPath.resolve(sourcePath.getFileName());
            assertTrue(Files.exists(destinationPath));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        //delete test.txt from destination folder
        File toDelete = new File(DESTINATION_FOLDER_PATH + "/test.txt");
        toDelete.delete();
    }

    @Test
    public void execute_duplicateFile_success() {
        UploadCommand uploadTestCommand = new UploadCommand(SOURCE_TEST_FILE, DESTINATION_FOLDER_PATH);
        UploadCommand uploadDuplicateCommand = new UploadCommand(SOURCE_DUPLICATE_FILE, DESTINATION_FOLDER_PATH);
        try {
            uploadTestCommand.execute(model);
            uploadDuplicateCommand.execute(model);
            String sourceText = new String(Files.readAllBytes(SOURCE_DUPLICATE_FILE));
            String destinationText =
                    new String(Files.readAllBytes(DESTINATION_FOLDER_PATH.resolve("test.txt")));
            assertTrue(destinationText.equals(sourceText));
        } catch (CommandException | IOException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }

        //delete test.txt from destination folder
        File toDelete = new File(DESTINATION_FOLDER_PATH + "/test.txt");
        toDelete.delete();
    }

    @Test
    public void execute_invalidPath_throwsCommandException() {
        Path sourcePath = INVALID_FILE_PATH;
        UploadCommand uploadCommand = new UploadCommand(sourcePath, sourcePath);
        assertCommandFailure(uploadCommand, model, UploadCommand.MESSAGE_INVALID_FILEPATH);

    }

    @Test
    void equals() {
        UploadCommand uploadCommand = new UploadCommand(SOURCE_TEST_FILE, DESTINATION_FOLDER_PATH);

        // same object -> returns true
        assertTrue(uploadCommand.equals(uploadCommand));

        // same values -> returns true
        UploadCommand uploadFirstCommandCopy = new UploadCommand(SOURCE_TEST_FILE, DESTINATION_FOLDER_PATH);
        assertTrue(uploadCommand.equals(uploadFirstCommandCopy));

        // different types -> returns false
        assertFalse(uploadCommand.equals(1));

        // null -> returns false
        assertFalse(uploadCommand.equals(null));

        // different source -> returns false
        assertFalse(uploadCommand.equals(new UploadCommand(SOURCE_DUPLICATE_FILE, DESTINATION_FOLDER_PATH)));

        // different destination -> returns false
        assertFalse(uploadCommand.equals(new UploadCommand(SOURCE_TEST_FILE, INVALID_FILE_PATH)));

        //different source and destination -> returns false
        assertFalse(uploadCommand.equals(new UploadCommand(SOURCE_DUPLICATE_FILE, INVALID_FILE_PATH)));
    }
}
