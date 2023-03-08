package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getNoPairsTypicalFriendlyLink;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyPair;
import seedu.address.storage.pair.JsonPairStorage;
import seedu.address.testutil.TypicalPairs;

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

    @Test
    public void readPair_invalidAndValidPair_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPair("invalidAndValidPair.json"));
    }

    @Test
    public void readAndSavePair_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPair.json");
        FriendlyLink original = getTypicalFriendlyLink();
        JsonPairStorage jsonPairStorage = new JsonPairStorage(filePath);

        // Save in new file and read back
        jsonPairStorage.savePair(original, filePath);
        ReadOnlyPair readBack = jsonPairStorage.readPair(filePath, getNoPairsTypicalFriendlyLink()).get();
        assertEquals(original.getPairList(), readBack.getPairList());

        // Modify data, overwrite exiting file, and read back
        original.removePair(TypicalPairs.PAIR1);
        original.addPair(TypicalPairs.PAIR1);
        jsonPairStorage.savePair(original, filePath);
        readBack = jsonPairStorage.readPair(filePath, getNoPairsTypicalFriendlyLink()).get();
        assertEquals(original.getPairList(), readBack.getPairList());

        // Save and read without specifying file path
        original.addPair(TypicalPairs.PAIR4);
        jsonPairStorage.savePair(original); // file path not specified
        readBack = jsonPairStorage.readPair(getNoPairsTypicalFriendlyLink()).get(); // file path not specified
        assertEquals(original.getPairList(), readBack.getPairList());
    }

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
