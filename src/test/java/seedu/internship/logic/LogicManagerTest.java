package seedu.internship.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.commons.core.Messages.MESSAGE_OUT_OF_RANGE_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.internship.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internship.logic.commands.CommandTestUtil.COMMENT_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.DATE_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.ROLE_DESC_APPLE;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_APPLE;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.APPLE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.ReadOnlyInternBuddy;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Internship;
import seedu.internship.storage.JsonInternBuddyStorage;
import seedu.internship.storage.JsonUserPrefsStorage;
import seedu.internship.storage.StorageManager;
import seedu.internship.testutil.InternshipBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInternBuddyStorage internBuddyStorage =
                new JsonInternBuddyStorage(temporaryFolder.resolve("internBuddy.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(internBuddyStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete-index 9";
        assertCommandException(deleteCommand, MESSAGE_OUT_OF_RANGE_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonInternBuddyIoExceptionThrowingStub
        JsonInternBuddyStorage internBuddyStorage =
                new JsonInternBuddyIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionInternBuddy.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(internBuddyStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + COMPANY_NAME_DESC_APPLE + ROLE_DESC_APPLE
                + STATUS_DESC_APPLE + DATE_DESC_APPLE + COMMENT_DESC_APPLE;
        Internship expectedInternship = new InternshipBuilder(APPLE).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addInternship(expectedInternship);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredInternshipList().remove(0));
    }

    @Test
    public void getInitialSelectedInternship_equalsNull_success() {
        assertEquals(logic.getSelectedInternship(), null);
    }

    @Test
    public void getNewSelectedInternship_equals_success() {
        model.updateSelectedInternship(APPLE);
        assertEquals(logic.getSelectedInternship(), APPLE);
    }

    @Test
    public void getModel_success() {
        assertEquals(logic.getModel(), model);
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getInternBuddy(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    @Test
    public void assertReadOnlyInternBuddySuccess() {
        assertEquals(logic.getInternBuddy(), model.getInternBuddy());
    }

    @Test
    public void assertGetInternBuddyFilePathSuccess() {
        assertEquals(logic.getInternBuddyFilePath(), model.getInternBuddyFilePath());
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonInternBuddyIoExceptionThrowingStub extends JsonInternBuddyStorage {
        private JsonInternBuddyIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveInternBuddy(ReadOnlyInternBuddy internBuddy, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
