package seedu.dengue.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.dengue.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.dengue.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.POSTAL_DESC_AMY;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dengue.logic.commands.AddCommand;
import seedu.dengue.logic.commands.CommandResult;
import seedu.dengue.logic.commands.ListCommand;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.Person;
import seedu.dengue.storage.CsvDengueHotspotStorage;
import seedu.dengue.storage.JsonUserPrefsStorage;
import seedu.dengue.storage.StorageManager;
import seedu.dengue.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        CsvDengueHotspotStorage dengueHotspotTrackerStorage =
                new CsvDengueHotspotStorage(temporaryFolder.resolve("dengueHotspotTracker.csv"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(dengueHotspotTrackerStorage, userPrefsStorage);
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
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonDengueHotspotTrackerIoExceptionThrowingStub
        CsvDengueHotspotStorage dengueHotspotTrackerStorage =
                new JsonDengueHotspotIoExceptionThrowingStub(temporaryFolder
                        .resolve("ioExceptionDengueHotspotTracker.csv"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(dengueHotspotTrackerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + POSTAL_DESC_AMY + DATE_DESC_AMY
                + AGE_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withVariants().build();
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
        Model expectedModel = new ModelManager(model.getDengueHotspotTracker(), new UserPrefs());
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
    private static class JsonDengueHotspotIoExceptionThrowingStub extends CsvDengueHotspotStorage {
        private JsonDengueHotspotIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, Path filePath)
                throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
