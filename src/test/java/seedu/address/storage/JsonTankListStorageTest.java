package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTanks.TANK_A;
import static seedu.address.testutil.TypicalTanks.TANK_D;
import static seedu.address.testutil.TypicalTanks.TANK_E;
import static seedu.address.testutil.TypicalTanks.getTypicalTankListVersionTwo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.TankList;

/**
 * Test class for {@code JsonTankListStorage}
 */
class JsonTankListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTankListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTankList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTankList(null));
    }

    private java.util.Optional<ReadOnlyTankList> readTankList(String filePath) throws Exception {
        return new JsonTankListStorage(Paths.get(filePath)).readTankList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTankList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTankList("notJsonFormatTankList.json"));
    }

    @Test
    public void readTankList_invalidTankTankList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTankList("invalidTankTankList.json"));
    }

    @Test
    public void readTankList_invalidAndValidTankTankList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTankList("invalidAndValidTankTankList.json"));
    }

    @Test
    public void readAndSaveTankList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTankList.json");
        TankList original = getTypicalTankListVersionTwo();
        JsonTankListStorage jsonTankListStorage = new JsonTankListStorage(filePath);

        // Save in new file and read back
        jsonTankListStorage.saveTankList(original, filePath);
        ReadOnlyTankList readBack = jsonTankListStorage.readTankList(filePath).get();
        assertEquals(original, new TankList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTank(TANK_D);
        original.removeTank(TANK_A);
        jsonTankListStorage.saveTankList(original, filePath);
        readBack = jsonTankListStorage.readTankList(filePath).get();
        assertEquals(original, new TankList(readBack));

        // Save and read without specifying file path
        original.addTank(TANK_E);
        jsonTankListStorage.saveTankList(original); // file path not specified
        readBack = jsonTankListStorage.readTankList().get(); // file path not specified
        assertEquals(original, new TankList(readBack));

    }

    @Test
    public void saveTankList_nullTankList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTankList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tankList} at the specified {@code filePath}.
     */
    private void saveTankList(ReadOnlyTankList tankList, String filePath) {
        try {
            new JsonTankListStorage(Paths.get(filePath))
                    .saveTankList(tankList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTankList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTankList(new TankList(), null));
    }
}
