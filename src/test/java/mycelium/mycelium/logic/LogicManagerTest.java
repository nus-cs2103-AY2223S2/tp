package mycelium.mycelium.logic;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.commands.CommandResult;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.ReadOnlyAddressBook;
import mycelium.mycelium.model.UserPrefs;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.storage.JsonAddressBookStorage;
import mycelium.mycelium.storage.JsonUserPrefsStorage;
import mycelium.mycelium.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private static final int NUM_OF_VALID_STATUSES = 3;


    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
            new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String cmd = "dp -pn apsdoifapfwoi";
        assertCommandException(cmd, Messages.MESSAGE_INVALID_PROJECT);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String cmd = String.format("c -cn %s -e %s", WEST.getName(), WEST.getEmail());
        assertCommandSuccess(cmd, String.format(AddClientCommand.MESSAGE_SUCCESS, WEST), model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
            new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add client command
        var name = "Alice";
        var email = "alice@baker.org";
        var client = new Client(new Name(name), new Email(email));
        var addCmd = AddClientCommand.COMMAND_ACRONYM + " -cn " + name + " -e " + email;
        var expModel = new ModelManager();
        expModel.addClient(client);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCmd, CommandException.class, expectedMessage, expModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredClientList().remove(0));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredProjectList().remove(0));
    }

    @Test
    public void getDueProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getDueProjectList().remove(0));
    }

    @Test
    public void getOverdueProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getOverdueProjectList().remove(0));
    }

    @Test
    public void getProjectStatistics_containsThreeKeys_success() {
        assertEquals(NUM_OF_VALID_STATUSES, logic.getProjectStatistics().size());
    }


    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
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
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
