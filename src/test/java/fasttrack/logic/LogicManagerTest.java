package fasttrack.logic;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static fasttrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fasttrack.commons.core.Messages;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.logic.commands.list.ListExpensesCommand;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.UserPrefs;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.expense.Expense;
import fasttrack.storage.JsonExpenseTrackerStorage;
import fasttrack.storage.JsonUserPrefsStorage;
import fasttrack.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model dataModel = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonExpenseTrackerStorage addressBookStorage = new JsonExpenseTrackerStorage(
                temporaryFolder.resolve("fastTrack.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(dataModel, storage);
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
        String listCommand = ListExpensesCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand,
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, dataModel.getFilteredExpenseList().size()),
                dataModel);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonExpenseTrackerStorage addressBookStorage = new JsonAddressBookIoExceptionThrowingStub(
                temporaryFolder.resolve("ioExceptionFastTrack.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
                temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(dataModel, storage);

        // Execute add command
        //String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
        //        + ADDRESS_DESC_AMY;
        //Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addExpense(new Expense("apples", "4.5", LocalDate.now(), new MiscellaneousCategory()));
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        //assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExpenseList().remove(0));
    }

    @Test
    public void getFilteredCategoryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCategoryList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedDataModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedDataModel, dataModel);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the
     * result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the
     * result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the
     * result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedDataModel = new ModelManager(dataModel.getExpenseTracker(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedDataModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedDataModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedDataModel, dataModel);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonExpenseTrackerStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveExpenseTracker(ReadOnlyExpenseTracker addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
