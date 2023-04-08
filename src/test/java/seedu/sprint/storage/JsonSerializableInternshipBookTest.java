package seedu.sprint.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sprint.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.exceptions.IllegalValueException;
import seedu.sprint.commons.util.JsonUtil;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.testutil.TypicalApplications;

public class JsonSerializableInternshipBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternshipBookTest");
    private static final Path TYPICAL_APPLICATIONS_FILE = TEST_DATA_FOLDER
            .resolve("typicalApplicationsInternshipBook.json");
    private static final Path INVALID_APPLICATION_FILE = TEST_DATA_FOLDER
            .resolve("invalidApplicationInternshipBook.json");
    private static final Path DUPLICATE_APPLICATION_FILE = TEST_DATA_FOLDER
            .resolve("duplicateApplicationInternshipBook.json");

    @Test
    public void toModelType_typicalApplicationsFile_success() throws Exception {
        JsonSerializableInternshipBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICATIONS_FILE,
                JsonSerializableInternshipBook.class).get();
        InternshipBook internshipBookFromFile = dataFromFile.toModelType();
        InternshipBook typicalApplicationsInternshipBook = TypicalApplications.getTypicalInternshipBook();

        assertEquals(internshipBookFromFile, typicalApplicationsInternshipBook);
    }

    @Test
    public void toModelType_invalidApplicationFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICATION_FILE,
                JsonSerializableInternshipBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplications_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICATION_FILE,
                JsonSerializableInternshipBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternshipBook.MESSAGE_DUPLICATE_APPLICATION,
                dataFromFile::toModelType);
    }
}
