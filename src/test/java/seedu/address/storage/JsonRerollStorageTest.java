package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.AMY;
import static seedu.address.testutil.TypicalEntities.BOB;
import static seedu.address.testutil.TypicalEntities.RAT;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.Reroll;

class JsonRerollStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readReroll_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readReroll(null));
    }

    private Optional<ReadOnlyReroll> readReroll(String filePath) throws Exception {
        return new JsonRerollStorage(Paths.get(filePath)).readReroll(addToTestDataIfNotNull(filePath));
    }

    private Path addToTestDataIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readReroll("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readReroll("notJsonFormatReroll.json"));
    }

    @Test
    public void readReroll_invalidEntityReroll_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readReroll("invalidEntityReroll.json"));
    }

    @Test
    public void readReroll_invalidAndValidEntityReroll_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readReroll("invalidAndValidEntityReroll.json"));
    }

    @Test
    public void readAndSaveReroll_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempReroll.json");
        Reroll original = getTypicalReroll();
        JsonRerollStorage jsonRerollStorage = new JsonRerollStorage(filePath);

        // Save in new file and read back
        jsonRerollStorage.saveReroll(original, filePath);
        ReadOnlyReroll readBack = jsonRerollStorage.readReroll(filePath).get();
        assertEquals(original, new Reroll(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEntity(AMY);
        original.deleteEntity(RAT);
        jsonRerollStorage.saveReroll(original, filePath);
        readBack = jsonRerollStorage.readReroll(filePath).get();
        assertEquals(original, new Reroll(readBack));

        // Save and read without specifying file path
        original.addEntity(BOB);
        jsonRerollStorage.saveReroll(original); // file path not specified
        readBack = jsonRerollStorage.readReroll().get(); // file path not specified
        assertEquals(original, new Reroll(readBack));
    }
}
