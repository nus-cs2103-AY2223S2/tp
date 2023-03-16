package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.AMAZON;
import static seedu.address.testutil.TypicalInternships.NITENDOGAMES;
import static seedu.address.testutil.TypicalInternships.TESLA;
import static seedu.address.testutil.TypicalInternships.getTypicalInternBuddy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.InternBuddy;
import seedu.address.model.ReadOnlyInternBuddy;

public class JsonInternBuddyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInternBuddyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternBuddy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternBuddy(null));
    }

    private java.util.Optional<ReadOnlyInternBuddy> readInternBuddy(String filePath) throws Exception {
        return new JsonInternBuddyStorage(Paths.get(filePath)).readInternBuddy(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternBuddy("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInternBuddy("notJsonFormatInternBuddy.json"));
    }

    @Test
    public void readInternBuddy_invalidPersonInternBuddy_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternBuddy("invalidInternshipInternBuddy.json"));
    }

    @Test
    public void readInternBuddy_invalidAndValidPersonInternBuddy_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternBuddy("invalidAndValidInternshipInternBuddy.json"));
    }

    @Test
    public void readAndSaveInternBuddy_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternBuddy.json");
        InternBuddy original = getTypicalInternBuddy();
        JsonInternBuddyStorage jsonInternBuddyStorage = new JsonInternBuddyStorage(filePath);

        // Save in new file and read back
        jsonInternBuddyStorage.saveInternBuddy(original, filePath);
        ReadOnlyInternBuddy readBack = jsonInternBuddyStorage.readInternBuddy(filePath).get();
        assertEquals(original, new InternBuddy(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(TESLA);
        original.removeInternship(AMAZON);
        jsonInternBuddyStorage.saveInternBuddy(original, filePath);
        readBack = jsonInternBuddyStorage.readInternBuddy(filePath).get();
        assertEquals(original, new InternBuddy(readBack));

        // Save and read without specifying file path
        original.addInternship(NITENDOGAMES);
        jsonInternBuddyStorage.saveInternBuddy(original); // file path not specified
        readBack = jsonInternBuddyStorage.readInternBuddy().get(); // file path not specified
        assertEquals(original, new InternBuddy(readBack));

    }

    @Test
    public void saveInternBuddy_nullInternBuddy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternBuddy(null, "SomeFile.json"));
    }

    /**
     * Saves {@code internBuddy} at the specified {@code filePath}.
     */
    private void saveInternBuddy(ReadOnlyInternBuddy internBuddy, String filePath) {
        try {
            new JsonInternBuddyStorage(Paths.get(filePath))
                    .saveInternBuddy(internBuddy, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternBuddy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternBuddy(new InternBuddy(), null));
    }
}
