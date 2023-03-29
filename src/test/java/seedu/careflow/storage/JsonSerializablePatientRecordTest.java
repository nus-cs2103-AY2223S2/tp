package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.careflow.storage.JsonSerializablePatientRecord.MESSAGE_DUPLICATE_PATIENT_IC;
import static seedu.careflow.storage.JsonSerializablePatientRecord.MESSAGE_DUPLICATE_PATIENT_NAME;
import static seedu.careflow.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.commons.util.JsonUtil;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.testutil.TypicalPatients;

class JsonSerializablePatientRecordTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePatientRecordTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsPatientRecord.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientPatientRecord.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientPatientRecord.json");
    private static final Path DUPLICATE_PATIENT_IC_FILE =
            TEST_DATA_FOLDER.resolve("duplicatePatientIcPatientRecord.json");

    @Test
    public void toModelType_typicalPatientFile_fail() throws Exception {
        JsonSerializablePatientRecord dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializablePatientRecord.class).get();
        PatientRecord patientRecordFromFile = dataFromFile.toModelType(); // without optional field
        PatientRecord typicalPatientsPatientRecord = TypicalPatients.getTypicalPatientRecord(); // with optional field
        assertNotEquals(patientRecordFromFile, typicalPatientsPatientRecord);
    }
    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecord dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializablePatientRecord.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecord dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializablePatientRecord.class).get();
        assertThrows(IllegalValueException.class, MESSAGE_DUPLICATE_PATIENT_NAME,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatientsIc_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecord dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_IC_FILE,
                JsonSerializablePatientRecord.class).get();
        assertThrows(IllegalValueException.class, MESSAGE_DUPLICATE_PATIENT_IC,
                dataFromFile::toModelType);
    }
}
