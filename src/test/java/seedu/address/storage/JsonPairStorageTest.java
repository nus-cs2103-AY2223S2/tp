package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyPair;
import seedu.address.storage.pair.JsonPairStorage;

public class JsonPairStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPairStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPair_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPair(null));
    }

    private java.util.Optional<ReadOnlyPair> readPair(String filePath) throws Exception {
        return new JsonPairStorage(Paths.get(filePath))
                .readPair(addToTestDataPathIfNotNull(filePath), new FriendlyLink());
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPair("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPair("notJsonFormatPair.json"));
    }

    @Test
    public void readPair_invalidPair_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPair("invalidPair.json"));
    }

    // TODO: readPair_invalidAndValidPair_throwDataConversionException() test case,
    //  can follow the one in elderlyStorageTest

    // TODO: readAndSavePair_allInOrder_success() test case, can follow the one in elderlyStorageTest

    @Test
    public void savePair_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePair(null, "SomeFile.json"));
    }

    /**
     * Saves {@code pair} at the specified {@code filePath}.
     */
    private void savePair(ReadOnlyPair pair, String filePath) {
        try {
            new JsonPairStorage(Paths.get(filePath))
                    .savePair(pair, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePair_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePair(new FriendlyLink(), null));
    }
}
