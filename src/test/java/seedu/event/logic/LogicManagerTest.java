package seedu.event.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.event.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.event.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.event.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.END_TIME_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.START_TIME_DESC_AMY;
import static seedu.event.testutil.Assert.assertThrows;
import static seedu.event.testutil.TypicalEvents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.event.logic.commands.AddCommand;
import seedu.event.logic.commands.CommandResult;
import seedu.event.logic.commands.ListCommand;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.UserPrefs;
import seedu.event.model.event.Event;
import seedu.event.storage.JsonContactListStorage;
import seedu.event.storage.JsonEventBookStorage;
import seedu.event.storage.JsonUserPrefsStorage;
import seedu.event.storage.StorageManager;
import seedu.event.testutil.EventBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonEventBookStorage eventBookStorage =
                new JsonEventBookStorage(temporaryFolder.resolve("eventBook.json"));
        JsonContactListStorage contactListStorage =
                new JsonContactListStorage(temporaryFolder.resolve("contactList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(eventBookStorage, contactListStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonEventBookIoExceptionThrowingStub
        JsonEventBookStorage eventBookStorage =
                new JsonEventBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionEventBook.json"));
        JsonContactListStorage contactListStorage =
                new JsonContactListIoExceptionThrowingStub(temporaryFolder
                        .resolve("ioExceptionContactList.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(eventBookStorage, contactListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + START_TIME_DESC_AMY + END_TIME_DESC_AMY;
        Event expectedEvent = new EventBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addEvent(expectedEvent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEventList().remove(0));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredContactList().remove(0));
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
        Model expectedModel = new ModelManager(model.getEventBook(), model.getContactList(), new UserPrefs());
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
    private static class JsonEventBookIoExceptionThrowingStub extends JsonEventBookStorage {
        private JsonEventBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveEventBook(ReadOnlyEventBook eventBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    private static class JsonContactListIoExceptionThrowingStub extends JsonContactListStorage {
        private JsonContactListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveContactList(ReadOnlyContactList eventBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
