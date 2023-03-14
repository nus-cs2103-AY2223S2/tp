package trackr.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static trackr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static trackr.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.logic.commands.AddCommand;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.ListCommand;
import trackr.logic.commands.exceptions.CommandException;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.ReadOnlyAddressBook;
import trackr.model.ReadOnlyTaskList;
import trackr.model.UserPrefs;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;
import trackr.storage.JsonTrackrStorage;
import trackr.storage.JsonUserPrefsStorage;
import trackr.storage.StorageManager;
import trackr.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTrackrStorage trackrStorage =
                new JsonTrackrStorage(temporaryFolder.resolve("trackr.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(trackrStorage, userPrefsStorage);
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
        // Setup LogicManager with JsonTrackrIoExceptionThrowingStub
        JsonTrackrStorage trackrStorage =
                new JsonTrackrIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTrackr.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(trackrStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Supplier expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }


    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }

    @Test
    public void getAddressBook() {
        ReadOnlyAddressBook expected = model.getAddressBook();
        assertEquals(expected, logic.getAddressBook());
    }

    @Test
    public void getTaskList() {
        ReadOnlyTaskList expected = model.getTaskList();
        assertEquals(expected, logic.getTaskList());
    }

    @Test
    public void getFilteredPersonList() {
        ObservableList<Supplier> expected = model.getFilteredPersonList();
        assertEquals(expected, logic.getFilteredPersonList());
    }

    @Test
    public void getFilteredTaskList() {
        ObservableList<Task> expected = model.getFilteredTaskList();
        assertEquals(expected, logic.getFilteredTaskList());
    }

    @Test
    public void getTrackrFilePath() {
        Path expected = model.getTrackrFilePath();
        assertEquals(expected, logic.getTrackrFilePath());
    }

    @Test
    public void getGuiSettings() {
        GuiSettings expected = model.getGuiSettings();
        assertEquals(expected, logic.getGuiSettings());
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
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
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
    private static class JsonTrackrIoExceptionThrowingStub extends JsonTrackrStorage {
        private JsonTrackrIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTrackr(ReadOnlyAddressBook addressBook, ReadOnlyTaskList taskList, Path filePath)
                throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
