package seedu.fitbook.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.fitbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.fitbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.CALORIE_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.fitbook.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.client.TypicalClients.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fitbook.logic.commands.AddCommand;
import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.ListClientsCommand;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.storage.JsonFitBookStorage;
import seedu.fitbook.storage.JsonUserPrefsStorage;
import seedu.fitbook.storage.StorageManager;
import seedu.fitbook.storage.routine.JsonFitBookExerciseRoutineStorage;
import seedu.fitbook.testutil.client.ClientBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private FitBookModel model = new FitBookModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonFitBookStorage fitBookStorage =
                new JsonFitBookStorage(temporaryFolder.resolve("fitBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonFitBookExerciseRoutineStorage fitBookExerciseRoutineStorage =
                new JsonFitBookExerciseRoutineStorage(temporaryFolder.resolve("exerciseRoutine.json"));
        StorageManager storage = new StorageManager(fitBookStorage, userPrefsStorage, fitBookExerciseRoutineStorage);
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
        String listCommand = ListClientsCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListClientsCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFitBookIoExceptionThrowingStub
        JsonFitBookStorage fitBookStorage =
                new JsonFitBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionFitBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonFitBookExerciseRoutineStorage fitBookExerciseRoutineStorage =
                new JsonFitBookExerciseRoutineStorage(temporaryFolder.resolve("ioExceptionExerciseRoutine.json"));
        StorageManager storage = new StorageManager(fitBookStorage, userPrefsStorage, fitBookExerciseRoutineStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + WEIGHT_DESC_AMY + GENDER_DESC_AMY + CALORIE_DESC_AMY;
        Client expectedClient = new ClientBuilder(AMY).withTags().withAppointments().build();
        FitBookModelManager expectedFitBookModel = new FitBookModelManager();
        expectedFitBookModel.addClient(expectedClient);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredClientList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedFitBookModel} <br>
     * @see #assertCommandFailure(String, Class, String, FitBookModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            FitBookModel expectedFitBookModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedFitBookModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, FitBookModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, FitBookModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, FitBookModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        FitBookModel expectedFitBookModel = new FitBookModelManager(model.getFitBook(),
                model.getFitBookExerciseRoutine(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedFitBookModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedFitBookModel} <br>
     * @see #assertCommandSuccess(String, String, FitBookModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, FitBookModel expectedFitBookModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedFitBookModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonFitBookIoExceptionThrowingStub extends JsonFitBookStorage {
        private JsonFitBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFitBook(ReadOnlyFitBook fitBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
