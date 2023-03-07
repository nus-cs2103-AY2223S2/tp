package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyElderly;
import seedu.address.storage.elderly.JsonElderlyStorage;
import seedu.address.testutil.TypicalElderly;

public class JsonElderlyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonElderlyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readElderly_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readElderly(null));
    }

    private java.util.Optional<ReadOnlyElderly> readElderly(String filePath) throws Exception {
        return new JsonElderlyStorage(Paths.get(filePath))
                .readElderly(addToTestDataPathIfNotNull(filePath), new FriendlyLink());
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readElderly("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readElderly("notJsonFormatElderly.json"));
    }

    @Test
    public void readElderly_invalidPersonElderly_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElderly("invalidElderly.json"));
    }

    @Test
    public void readElderly_invalidAndValidPersonElderly_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElderly("invalidAndValidElderly.json"));
    }

    @Test
    public void readAndSaveElderly_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempElderly.json");
        FriendlyLink original = getTypicalFriendlyLink();
        JsonElderlyStorage jsonElderlyStorage = new JsonElderlyStorage(filePath);

        // Save in new file and read back
        jsonElderlyStorage.saveElderly(original, filePath);
        ReadOnlyElderly readBack = jsonElderlyStorage.readElderly(filePath, new FriendlyLink()).get();
        assertEquals(original.getElderlyList(), readBack.getElderlyList());

        // Modify data, overwrite exiting file, and read back
        original.removeElderly(TypicalElderly.ALICE);
        original.addElderly(TypicalElderly.ALICE);
        jsonElderlyStorage.saveElderly(original, filePath);
        readBack = jsonElderlyStorage.readElderly(filePath, new FriendlyLink()).get();
        assertEquals(original.getElderlyList(), readBack.getElderlyList());

        // Save and read without specifying file path
        original.addElderly(TypicalElderly.AMY);
        jsonElderlyStorage.saveElderly(original); // file path not specified
        readBack = jsonElderlyStorage.readElderly(new FriendlyLink()).get(); // file path not specified
        assertEquals(original.getElderlyList(), readBack.getElderlyList());

    }

    @Test
    public void saveElderly_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveElderly(null, "SomeFile.json"));
    }

    /**
     * Saves {@code elderly} at the specified {@code filePath}.
     */
    private void saveElderly(ReadOnlyElderly elderly, String filePath) {
        try {
            new JsonElderlyStorage(Paths.get(filePath))
                    .saveElderly(elderly, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveElderly_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveElderly(new FriendlyLink(), null));
    }
}
