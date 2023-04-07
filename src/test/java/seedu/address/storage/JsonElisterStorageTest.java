package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalElister;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Elister;
import seedu.address.model.ReadOnlyElister;

public class JsonElisterStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonElisterStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readElister_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readElister(null));
    }

    private java.util.Optional<ReadOnlyElister> readElister(String filePath) throws Exception {
        return new JsonElisterStorage(Paths.get(filePath)).readElister(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readElister("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readElister("notJsonFormatElister.json"));
    }

    @Test
    public void readElister_invalidPersonElister_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElister("invalidPersonElister.json"));
    }

    @Test
    public void readElister_invalidAndValidPersonElister_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElister("invalidAndValidPersonElister.json"));
    }

    @Test
    public void readAndSaveElister_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempElister.json");
        Elister original = getTypicalElister();
        JsonElisterStorage jsonElisterStorage = new JsonElisterStorage(filePath);

        // Save in new file and read back
        jsonElisterStorage.saveElister(original, filePath);
        ReadOnlyElister readBack = jsonElisterStorage.readElister(filePath).get();
        assertEquals(original, new Elister(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonElisterStorage.saveElister(original, filePath);
        readBack = jsonElisterStorage.readElister(filePath).get();
        assertEquals(original, new Elister(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonElisterStorage.saveElister(original); // file path not specified
        readBack = jsonElisterStorage.readElister().get(); // file path not specified
        assertEquals(original, new Elister(readBack));

    }

    @Test
    public void saveElister_nullElister_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveElister(null, "SomeFile.json"));
    }

    /**
     * Saves {@code elister} at the specified {@code filePath}.
     */
    private void saveElister(ReadOnlyElister elister, String filePath) {
        try {
            new JsonElisterStorage(Paths.get(filePath))
                    .saveElister(elister, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveElister_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveElister(new Elister(), null));
    }
}
