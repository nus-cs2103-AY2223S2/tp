package taa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import taa.commons.exceptions.IllegalValueException;
import taa.commons.util.JsonUtil;
import taa.model.ClassList;
import taa.model.ReadOnlyStudentList;
import taa.testutil.Assert;
import taa.testutil.TypicalPersons;

public class JsonSerializableClassListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableClassListTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTaaData.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTaaData.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTaaData.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTaaData dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTaaData.class).get();
        ReadOnlyStudentList classListFromFile = dataFromFile.toModelType().studentList;
        ClassList typicalPersonsClassList = TypicalPersons.getTypicalTaaData();
        assertEquals(classListFromFile, typicalPersonsClassList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaaData dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTaaData.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTaaData dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTaaData.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableTaaData.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
