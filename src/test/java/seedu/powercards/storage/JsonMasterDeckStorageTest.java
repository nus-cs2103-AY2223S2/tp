package seedu.powercards.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.GRAVITY;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.SMOG;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.powercards.commons.exceptions.DataConversionException;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.ReadOnlyMasterDeck;

public class JsonMasterDeckStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMasterDeckStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMasterDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMasterDeck(null));
    }

    private java.util.Optional<ReadOnlyMasterDeck> readMasterDeck(String filePath) throws Exception {
        return new JsonMasterDeckStorage(Paths.get(filePath)).readMasterDeck(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMasterDeck("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMasterDeck("notJsonFormatMasterDeck.json"));
    }

    @Test
    public void readMasterDeck_invalidCardMasterDeck_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMasterDeck("invalidCardMasterDeck.json"));
    }

    //    @Test
    //    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, ()
    //        -> readAddressBook("invalidAndValidCardMasterDeck.json"));
    //    }

    @Test
    public void readAndSaveMasterDeck_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMasterDeck.json");
        MasterDeck original = getTypicalMasterDeck();
        JsonMasterDeckStorage jsonMasterDeckStorage = new JsonMasterDeckStorage(filePath);

        // Save in new file and read back
        jsonMasterDeckStorage.saveMasterDeck(original, filePath);
        ReadOnlyMasterDeck readBack = jsonMasterDeckStorage.readMasterDeck(filePath).get();
        assertEquals(original, new MasterDeck(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCard(SMOG);
        original.removeCard(LOOP);
        jsonMasterDeckStorage.saveMasterDeck(original, filePath);
        readBack = jsonMasterDeckStorage.readMasterDeck(filePath).get();
        assertEquals(original, new MasterDeck(readBack));

        // Save and read without specifying file path
        original.addCard(GRAVITY);
        jsonMasterDeckStorage.saveMasterDeck(original); // file path not specified
        readBack = jsonMasterDeckStorage.readMasterDeck().get(); // file path not specified
        assertEquals(original, new MasterDeck(readBack));

    }

    @Test
    public void saveMasterDeck_nullMasterDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMasterDeck(null, "SomeFile.json"));
    }

    /**
     * Saves {@code masterDeck} at the specified {@code filePath}.
     */
    private void saveMasterDeck(ReadOnlyMasterDeck masterDeck, String filePath) {
        try {
            new JsonMasterDeckStorage(Paths.get(filePath))
                    .saveMasterDeck(masterDeck, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMasterDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMasterDeck(new MasterDeck(), null));
    }
}
