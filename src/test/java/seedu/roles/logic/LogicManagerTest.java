package seedu.roles.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.roles.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.roles.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.roles.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.EXPERIENCE_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.JOBDESCRIPTION_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.roles.logic.commands.CommandTestUtil.WEBSITE;
import static seedu.roles.testutil.Assert.assertThrows;
import static seedu.roles.testutil.TypicalRoles.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.roles.logic.commands.AddCommand;
import seedu.roles.logic.commands.CommandResult;
import seedu.roles.logic.commands.ListCommand;
import seedu.roles.logic.commands.exceptions.CommandException;
import seedu.roles.logic.commands.exceptions.exceptions.ParseException;
import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.ReadOnlyRoleBook;
import seedu.roles.model.UserPrefs;
import seedu.roles.model.job.Role;
import seedu.roles.storage.JsonRoleBookStorage;
import seedu.roles.storage.JsonUserPrefsStorage;
import seedu.roles.storage.StorageManager;
import seedu.roles.testutil.RoleBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonRoleBookStorage roleBookStorage =
                new JsonRoleBookStorage(temporaryFolder.resolve("TechTrack.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(roleBookStorage, userPrefsStorage);
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
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonRoleBookStorage roleBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionRoleBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(roleBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + JOBDESCRIPTION_DESC_AMY + WEBSITE
                + SALARY_DESC_AMY + DEADLINE_DESC_AMY + EXPERIENCE_DESC_AMY;
        Role expectedRole = new RoleBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addRole(expectedRole);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredRoleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredRoleList().remove(0));
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
        assertEquals(expectedMessage, result.getOutput());
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
        Model expectedModel = new ModelManager(model.getRoleBook(), new UserPrefs());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonRoleBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveRoleBook(ReadOnlyRoleBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
