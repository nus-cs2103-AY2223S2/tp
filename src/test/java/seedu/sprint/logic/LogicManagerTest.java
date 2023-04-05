package seedu.sprint.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.sprint.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.COMPANY_EMAIL_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.COMPANY_NAME_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.ROLE_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.STATUS_DESC_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.TAG_DESC_HIGHSALARY;
import static seedu.sprint.testutil.Assert.assertThrows;
import static seedu.sprint.testutil.TypicalApplications.BYTEDANCE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.sprint.logic.commands.AddApplicationCommand;
import seedu.sprint.logic.commands.CommandResult;
import seedu.sprint.logic.commands.ListCommand;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.ReadOnlyInternshipBook;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.storage.JsonInternshipBookStorage;
import seedu.sprint.storage.JsonUserPrefsStorage;
import seedu.sprint.storage.StorageManager;
import seedu.sprint.testutil.ApplicationBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookStorage(temporaryFolder.resolve("sprint.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(internshipBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete-app 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }


    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonInternshipBookIoExceptionThrowingStub
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionInternshipBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(internshipBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addApplicationCommand = AddApplicationCommand.COMMAND_WORD
                + ROLE_DESC_BYTEDANCE + COMPANY_NAME_DESC_BYTEDANCE + COMPANY_EMAIL_DESC_BYTEDANCE
                + STATUS_DESC_BYTEDANCE + TAG_DESC_HIGHSALARY;
        Application expectedApplication = new ApplicationBuilder(BYTEDANCE).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addApplication(expectedApplication);
        expectedModel.commitInternshipBookChange();
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addApplicationCommand, CommandException.class, expectedMessage, expectedModel);
    }


    @Test
    public void getFilteredApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredApplicationList().remove(0));
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
        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
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

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonInternshipBookIoExceptionThrowingStub extends JsonInternshipBookStorage {
        private JsonInternshipBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
