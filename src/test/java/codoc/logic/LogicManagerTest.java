package codoc.logic;

import static codoc.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static codoc.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static codoc.logic.commands.CommandTestUtil.COURSE_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.LINKEDIN_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static codoc.testutil.Assert.assertThrows;
import static codoc.testutil.TypicalPersons.AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import codoc.logic.commands.AddCommand;
import codoc.logic.commands.CommandResult;
import codoc.logic.commands.ListCommand;
import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.ReadOnlyCodoc;
import codoc.model.UserPrefs;
import codoc.model.person.Person;
import codoc.storage.JsonCodocStorage;
import codoc.storage.JsonUserPrefsStorage;
import codoc.storage.StorageManager;
import codoc.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonCodocStorage codocStorage =
                new JsonCodocStorage(temporaryFolder.resolve("Codoc.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(codocStorage, userPrefsStorage);
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
        // Setup LogicManager with JsonCodocIoExceptionThrowingStub
        JsonCodocStorage codocStorage =
                new JsonCodocIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionCodoc.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(codocStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand =
                AddCommand.COMMAND_WORD + NAME_DESC_AMY
                        + COURSE_DESC_AMY + YEAR_DESC_AMY
                        + GITHUB_DESC_AMY + EMAIL_DESC_AMY
                + LINKEDIN_DESC_AMY;
        //make sure expected person and add command results in the same person
        Person expectedPerson = new PersonBuilder(AMY)
                .withSkills().withModules().build();
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
        Model expectedModel = new ModelManager(model.getCodoc(), new UserPrefs());
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
    private static class JsonCodocIoExceptionThrowingStub extends JsonCodocStorage {
        private JsonCodocIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveCodoc(ReadOnlyCodoc codoc, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
