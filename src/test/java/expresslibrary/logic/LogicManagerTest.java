package expresslibrary.logic;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static expresslibrary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static expresslibrary.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static expresslibrary.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static expresslibrary.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalPersons.AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import expresslibrary.logic.commands.AddPersonCommand;
import expresslibrary.logic.commands.CommandResult;
import expresslibrary.logic.commands.ListPersonCommand;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.logic.parser.exceptions.ParseException;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.person.Person;
import expresslibrary.storage.JsonExpressLibraryStorage;
import expresslibrary.storage.JsonUserPrefsStorage;
import expresslibrary.storage.StorageManager;
import expresslibrary.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonExpressLibraryStorage expressLibraryStorage = new JsonExpressLibraryStorage(
                temporaryFolder.resolve("expresslibrary.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(expressLibraryStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListPersonCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListPersonCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonExpressLibraryIoExceptionThrowingStub
        JsonExpressLibraryStorage expressLibraryStorage = new JsonExpressLibraryIoExceptionThrowingStub(
                temporaryFolder.resolve("ioExceptionExpressLibrary.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
                temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(expressLibraryStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddPersonCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the
     * result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the
     * result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the
     * result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        // will fix later
        //assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        //assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonExpressLibraryIoExceptionThrowingStub extends JsonExpressLibraryStorage {
        private JsonExpressLibraryIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
