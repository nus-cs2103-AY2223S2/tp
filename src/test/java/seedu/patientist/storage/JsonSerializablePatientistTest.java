package seedu.patientist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.patientist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.commons.util.JsonUtil;
import seedu.patientist.model.Patientist;
import seedu.patientist.testutil.TypicalPatients;

public class JsonSerializablePatientistTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePatientistTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientPatientist.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPatientPatientist.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientPatientist.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializablePatientist dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializablePatientist.class).get();
        Patientist patientistFromFile = dataFromFile.toModelType();
        Patientist typicalPersonsPatientist = TypicalPatients.getTypicalPatientist();
        assertEquals(patientistFromFile, typicalPersonsPatientist);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientist dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializablePatientist.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializablePatientist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePatientist.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
