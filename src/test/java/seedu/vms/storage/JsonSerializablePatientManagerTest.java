package seedu.vms.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.testutil.TypicalPatients;

public class JsonSerializablePatientManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePatientManagerTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsPatientManager.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientPatientManager.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientPatientManager.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializablePatientManager dataFromFile = JsonUtil.deserializeFromFile(TYPICAL_PATIENTS_FILE,
                JsonSerializablePatientManager.class);
        PatientManager patientManagerFromFile = dataFromFile.toModelType();
        PatientManager typicalPatientsPatientManager = TypicalPatients.getTypicalPatientManager();
        assertEquals(patientManagerFromFile, typicalPatientsPatientManager);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientManager dataFromFile = JsonUtil.deserializeFromFile(INVALID_PATIENT_FILE,
                JsonSerializablePatientManager.class);
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateId_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientManager dataFromFile = JsonUtil.deserializeFromFile(DUPLICATE_PATIENT_FILE,
                JsonSerializablePatientManager.class);
        assertThrows(IllegalValueException.class, JsonSerializablePatientManager.DUPLICATE_ID,
                dataFromFile::toModelType);
    }

}
