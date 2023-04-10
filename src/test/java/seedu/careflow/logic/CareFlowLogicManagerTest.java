package seedu.careflow.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_DRUG_DISPLAYED_INDEX;
import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.BIRTHDATE_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.DRUG_ALLERGY_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.EMERGENCY_CONTACT_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.GENDER_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.IC_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.NAME_DESC_AMY;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.PHONE_DESC_AMY;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.logic.commands.generalcommand.HelpCommand;
import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.logic.commands.patientcommands.ListCommand;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.storage.CareFlowStorageManager;
import seedu.careflow.storage.JsonDrugInventoryStorage;
import seedu.careflow.storage.JsonPatientRecordStorage;
import seedu.careflow.storage.JsonUserPrefsStorage;
import seedu.careflow.testutil.PatientBuilder;

class CareFlowLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private CareFlowModel model = new CareFlowModelManager();
    private CareFlowLogic logic;

    @BeforeEach
    public void setUp() {
        JsonPatientRecordStorage patientStorage =
                new JsonPatientRecordStorage(temporaryFolder.resolve("patientRecord.json"));
        JsonDrugInventoryStorage drugStorage =
                new JsonDrugInventoryStorage(temporaryFolder.resolve("drugInventory.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        CareFlowStorageManager storage = new CareFlowStorageManager(patientStorage, drugStorage, userPrefsStorage);
        logic = new CareFlowLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_patientCommandExecutionError_throwsCommandException() {
        String deleteCommand = "p delete -i 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_drugCommandExecutionError_throwsCommandException() {
        String deleteCommand = "d delete -i 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_DRUG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validPatientCommand_success() throws Exception {
        String listCommand = "p " + ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup CareFlowLogicManager with JsonCareFlowIoExceptionThrowingStub
        JsonPatientRecordStorage patientRecordStorage =
                new JsonPatientRecordIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionPatient.json"));
        JsonDrugInventoryStorage drugInventoryStorage =
                new JsonDrugInventoryIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionDrug.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        CareFlowStorageManager storage = new CareFlowStorageManager(patientRecordStorage,
                drugInventoryStorage, userPrefsStorage);
        logic = new CareFlowLogicManager(model, storage);

        // Execute add command
        String addCommand = "p " + AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + BIRTHDATE_DESC_AMY + GENDER_DESC_AMY + IC_DESC_AMY + DRUG_ALLERGY_DESC_AMY
                + EMERGENCY_CONTACT_DESC_AMY;
        Patient expectedPatient = new PatientBuilder(AMY).build();
        CareFlowModelManager expectedModel = new CareFlowModelManager();
        expectedModel.addPatient(expectedPatient);
        String expectedMessage = CareFlowLogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPatientList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, CareFlowModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      CareFlowModel expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, CareFlowModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, CareFlowModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, CareFlowModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        CareFlowModel expectedModel = new CareFlowModelManager(model.getPatientRecord(),
                model.getDrugInventory(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, CareFlowModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, CareFlowModel expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel.getPatientRecord(), model.getPatientRecord());
        assertEquals(expectedModel.getDrugInventory(), model.getDrugInventory());
        assertEquals(expectedModel.getHospitalList(), model.getHospitalList());
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonPatientRecordIoExceptionThrowingStub extends JsonPatientRecordStorage {
        private JsonPatientRecordIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePatientRecord(ReadOnlyPatientRecord patientRecord, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    private static class JsonDrugInventoryIoExceptionThrowingStub extends JsonDrugInventoryStorage {
        private JsonDrugInventoryIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveDrugInventory(ReadOnlyDrugInventory drugInventory, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
