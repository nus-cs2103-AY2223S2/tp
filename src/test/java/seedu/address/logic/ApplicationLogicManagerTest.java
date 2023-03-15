package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.ROLE_DESC_ONE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.COMPANY_NAME_DESC_ONE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.COMPANY_EMAIL_DESC_ONE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.STATUS_DESC_ONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddApplicationCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListApplicationCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.storage.JsonInternshipBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.ApplicationStorageManager;
import seedu.address.testutil.ApplicationBuilder;

public class ApplicationLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private ApplicationModel model = new ApplicationModelManager();
    private ApplicationLogic logic;

    @BeforeEach
    public void setUp() {
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        ApplicationStorageManager storage = new ApplicationStorageManager(internshipBookStorage, userPrefsStorage);
        logic = new ApplicationLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListApplicationCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListApplicationCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup ApplicationLogicManager with JsonInternshipBookIoExceptionThrowingStub
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        ApplicationStorageManager storage = new ApplicationStorageManager(internshipBookStorage, userPrefsStorage);
        logic = new ApplicationLogicManager(model, storage);

        // Execute add command
        String addApplicationCommand = AddApplicationCommand.COMMAND_WORD
                + ROLE_DESC_ONE + COMPANY_NAME_DESC_ONE + COMPANY_EMAIL_DESC_ONE + STATUS_DESC_ONE;
        Application expectedApplication = new ApplicationBuilder(BYTEDANCE).build();
        ApplicationModelManager expectedModel = new ApplicationModelManager();
        expectedModel.addApplication(expectedApplication);
        String expectedMessage = ApplicationLogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
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
     * @see #assertCommandFailure(String, Class, String, ApplicationModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      ApplicationModel expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, ApplicationModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, ApplicationModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, ApplicationModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        ApplicationModel expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, ApplicationModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, ApplicationModel expectedModel) {
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
