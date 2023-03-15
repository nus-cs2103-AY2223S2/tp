package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.commons.util.JsonUtil;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.testutil.TypicalInternships;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternshipCatalogueTest");
    private static final Path TYPICAL_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("typicalInternshipsInternshipCatalogue.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("invalidInternshipInternshipCatalogue.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("duplicateInternshipInternshipCatalogue.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableInternshipCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIP_FILE,
                JsonSerializableInternshipCatalogue.class).get();
        InternshipCatalogue internshipCatalogueFromFile = dataFromFile.toModelType();
        InternshipCatalogue typicalInternshipsInternshipCatalogue = TypicalInternships.getTypicalInternshipCatalogue();
        assertEquals(internshipCatalogueFromFile, typicalInternshipsInternshipCatalogue);
    }

    @Test
    public void toModelType_invalidInternshipsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableInternshipCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipCatalogue dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableInternshipCatalogue.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternshipCatalogue.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
