package tfifteenfour.clipboard.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.commons.util.JsonUtil;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.testutil.TypicalStudents;

public class JsonSerializableRosterTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRosterTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsRoster.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentRoster.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentRoster.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableRoster dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableRoster.class).get();
        Roster addressBookFromFile = dataFromFile.toModelType();
        Roster typicalStudentsRoster = TypicalStudents.getTypicalRoster();
        assertEquals(addressBookFromFile, typicalStudentsRoster);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRoster dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableRoster.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableRoster dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableRoster.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRoster.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
