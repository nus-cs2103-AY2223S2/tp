package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.GRAVITY;
import static seedu.address.testutil.TypicalCards.LOOP;
import static seedu.address.testutil.TypicalCards.SMOG;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MasterDeck;
import seedu.address.model.ReadOnlyMasterDeck;

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
        assertThrows(DataConversionException.class, () -> readMasterDeck("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readMasterDeck_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMasterDeck("invalidPersonAddressBook.json"));
    }

    //    @Test
    //    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, ()
    //        -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    //    }

    @Test
    public void readAndSaveMasterDeck_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
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
    public void saveMasterDeck_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMasterDeck(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveMasterDeck(ReadOnlyMasterDeck addressBook, String filePath) {
        try {
            new JsonMasterDeckStorage(Paths.get(filePath))
                    .saveMasterDeck(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMasterDeck(new MasterDeck(), null));
    }
}
