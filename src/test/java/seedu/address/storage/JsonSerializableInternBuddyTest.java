package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.InternBuddy;
import seedu.address.testutil.TypicalInternships;

public class JsonSerializableInternBuddyTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableInternBuddyTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE =
            TEST_DATA_FOLDER.resolve("typicalInternshipInternBuddy.json");
    private static final Path INVALID_INTERNSHIP_FILE =
            TEST_DATA_FOLDER.resolve("invalidInternshipInternBuddy.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE =
            TEST_DATA_FOLDER.resolve("duplicateInternshipInternBuddy.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableInternBuddy dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableInternBuddy.class).get();
        InternBuddy internBuddyFromFile = dataFromFile.toModelType();
        InternBuddy typicalInternshipsInternBuddy = TypicalInternships.getTypicalInternBuddy();
        assertEquals(internBuddyFromFile, typicalInternshipsInternBuddy);
    }

    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternBuddy dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableInternBuddy.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableInternBuddy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableInternBuddy.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternBuddy.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
