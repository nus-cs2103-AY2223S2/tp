package seedu.medinfo.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medinfo.commons.core.Messages.MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW;
import static seedu.medinfo.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.medinfo.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.medinfo.testutil.Assert.assertThrows;
import static seedu.medinfo.testutil.TypicalPatients.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medinfo.logic.commands.AddCommand;
import seedu.medinfo.logic.commands.CommandResult;
import seedu.medinfo.logic.commands.ListCommand;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.logic.parser.exceptions.ParseException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.UserPrefs;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.storage.JsonMedInfoStorage;
import seedu.medinfo.storage.JsonUserPrefsStorage;
import seedu.medinfo.storage.StorageManager;
import seedu.medinfo.testutil.PatientBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonMedInfoStorage medInfoStorage = new JsonMedInfoStorage(
                temporaryFolder.resolve("medinfo.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(medInfoStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    //    @Test
    //    public void execute_commandExecutionError_throwsCommandException() {
    //        String deleteCommand = "delete 9";
    //        String expectedMessage = MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
    //        assertCommandException(deleteCommand, expectedMessage);
    //    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String expectedMessage = String.format(MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW, 0);
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, expectedMessage, model);
    }


    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonMedInfoIoExceptionThrowingStub
        JsonMedInfoStorage addressBookStorage = new
            JsonMedInfoIoExceptionThrowingStub(
            temporaryFolder.resolve("ioExceptionMedInfo.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
            temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage,
            userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + NRIC_DESC_AMY + STATUS_DESC_AMY;
        Patient expectedPatient = new PatientBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        try {
            expectedModel.addPatient(expectedPatient);
        } catch (CommandException e) {
            System.out.println("Caught CommandException error!!!");
        }
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPatientList().remove(0));
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
        Model expectedModel = new ModelManager(model.getMedInfo(), new UserPrefs());
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
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonMedInfoIoExceptionThrowingStub extends JsonMedInfoStorage {
        private JsonMedInfoIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveMedInfo(ReadOnlyMedInfo addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
