package seedu.wife.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalFood.MEIJI;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.wife.commons.exceptions.DataConversionException;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.Wife;
import seedu.wife.testutil.TypicalWife;

public class JsonWifeStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWifeStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWife_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWife(null));
    }

    private java.util.Optional<ReadOnlyWife> readWife(String filePath) throws Exception {
        return new JsonWifeStorage(Paths.get(filePath)).readWife(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWife("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readWife("notJsonFormatWife.json"));
    }

    @Test
    public void readWife_invalidFoodWife_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWife("invalidFoodWife.json"));
    }

    @Test
    public void readWife_invalidFoodWife_throwNumberFormatExceptionException() {
        assertThrows(DataConversionException.class, () -> readWife("invalidFoodQuantityWife.json"));
    }

    @Test
    public void readWife_invalidAndValidFood_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWife("invalidAndValidFoodWife.json"));
    }

    @Test
    public void readAndSaveWife_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWife.json");
        Wife original = TypicalWife.getTypicalWife();
        JsonWifeStorage jsonWifeStorage = new JsonWifeStorage(filePath);

        // Save in new file and read back
        jsonWifeStorage.saveWife(original, filePath);
        ReadOnlyWife readBack = jsonWifeStorage.readWife(filePath).get();
        assertEquals(original, new Wife(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeFood(MEIJI);
        jsonWifeStorage.saveWife(original, filePath);
        readBack = jsonWifeStorage.readWife(filePath).get();
        assertEquals(original, new Wife(readBack));

        // Save and read without specifying file path
        original.addFood(MEIJI);
        jsonWifeStorage.saveWife(original); // file path not specified
        readBack = jsonWifeStorage.readWife().get(); // file path not specified
        assertEquals(original, new Wife(readBack));

    }

    @Test
    public void saveWife_nullWife_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWife(null, "SomeFile.json"));
    }

    /**
     * Saves {@code WIFE} at the specified {@code filePath}.
     */
    private void saveWife(ReadOnlyWife wife, String filePath) {
        try {
            new JsonWifeStorage(Paths.get(filePath))
                    .saveWife(wife, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWife_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWife(new Wife(), null));
    }
}
