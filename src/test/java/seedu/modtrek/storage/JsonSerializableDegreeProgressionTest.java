package seedu.modtrek.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.modtrek.commons.exceptions.IllegalValueException;
import seedu.modtrek.commons.util.JsonUtil;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.testutil.TypicalModules;

public class JsonSerializableDegreeProgressionTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableDegreeProgressionTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER
            .resolve("typicalModuleDegreeProgression.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER
            .resolve("invalidModuleDegreeProgression.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER
            .resolve("duplicateModuleDegreeProgression.json");

    @Test
    public void toModelType_typicalModulesFile_success() throws Exception {
        JsonSerializableDegreeProgression dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableDegreeProgression.class).get();
        DegreeProgression addressBookFromFile = dataFromFile.toModelType();
        DegreeProgression typicalModulesDegreeProgression = TypicalModules.getTypicalDegreeProgression();
        assertEquals(addressBookFromFile, typicalModulesDegreeProgression);
    }

    @Test
    public void toModelType_invalidModuleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDegreeProgression dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableDegreeProgression.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableDegreeProgression dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableDegreeProgression.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDegreeProgression.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

}
