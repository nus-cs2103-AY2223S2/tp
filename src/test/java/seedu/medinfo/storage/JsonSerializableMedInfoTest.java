package seedu.medinfo.storage;

import static seedu.medinfo.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.medinfo.commons.exceptions.IllegalValueException;
import seedu.medinfo.commons.util.JsonUtil;

public class JsonSerializableMedInfoTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMedInfoTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsMedInfo.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientMedInfo.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientMedInfo.json");

    //    @Test
    //    public void toModelType_typicalPatientsFile_success() throws Exception {
    //        JsonSerializableMedInfo dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
    //                JsonSerializableMedInfo.class).get();
    //        MedInfo medInfoFromFile = dataFromFile.toModelType();
    //        MedInfo typicalPatientsMedInfo = TypicalPatients.getTypicalMedInfo();
    //        assertEquals(medInfoFromFile, typicalPatientsMedInfo);
    //    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMedInfo dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableMedInfo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableMedInfo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializableMedInfo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMedInfo.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
