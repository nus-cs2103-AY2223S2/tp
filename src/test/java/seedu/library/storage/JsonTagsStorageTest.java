package seedu.library.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalTags.OCEAN;
import static seedu.library.testutil.TypicalTags.PLANT;
import static seedu.library.testutil.TypicalTags.getTypicalTags;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.model.ReadOnlyTags;
import seedu.library.model.Tags;
import seedu.library.model.tag.Tag;

public class JsonTagsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTagsTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTags_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTags(null));
    }

    private java.util.Optional<ReadOnlyTags> readTags(String filePath) throws Exception {
        return new JsonTagsStorage(Paths.get(filePath)).readTags(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTags("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTags("notJsonFormatTags.json"));
    }

    @Test
    public void readLibrary_invalidTags_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTags("invalidTags.json"));
    }

    @Test
    public void readLibrary_invalidAndValidTags_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTags("invalidTags.json"));
    }

    @Test
    public void readAndSaveTags_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTags.json");
        Tags original = getTypicalTags();
        JsonTagsStorage jsonTagsStorage = new JsonTagsStorage(filePath);

        // Save in new file and read back
        jsonTagsStorage.saveTags(original, filePath);
        ReadOnlyTags readBack = jsonTagsStorage.readTags(filePath).get();
        assertEquals(original, new Tags(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTag(OCEAN);
        original.removeTag(new Tag("MaleProtagonist"));
        jsonTagsStorage.saveTags(original, filePath);
        readBack = jsonTagsStorage.readTags(filePath).get();
        assertEquals(original, new Tags(readBack));

        // Save and read without specifying file path
        original.addTag(PLANT);
        jsonTagsStorage.saveTags(original); // file path not specified
        readBack = jsonTagsStorage.readTags().get(); // file path not specified
        assertEquals(original, new Tags(readBack));

    }

    @Test
    public void saveTags_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTags(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Tags} at the specified {@code filePath}.
     */
    private void saveTags(ReadOnlyTags tags, String filePath) {
        try {
            new JsonTagsStorage(Paths.get(filePath))
                    .saveTags(tags, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTags_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTags(new Tags(), null));
    }
}
