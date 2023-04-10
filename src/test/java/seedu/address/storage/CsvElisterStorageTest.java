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

public class CsvElisterStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "CsvElisterStorageTest");
    @TempDir
    public Path testFolder;

    @Test
    public void readElister_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readElister(null));
    }

    private java.util.Optional<ReadOnlyElister> readElister(String filePath) throws Exception {
        return new CsvElisterStorage(Paths.get(filePath)).readElister(addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readElister("NonExistentFile.csv").isPresent());
    }

    @Test
    public void readElister_tooManyHeaders_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElister("TooManyHeaders.csv"));
    }

    @Test
    public void readElister_wrongHeaders_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElister("WrongHeaders.csv"));
    }

    @Test
    public void readElister_missingFields_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElister("MissingFields.csv"));
    }

    @Test
    public void readElister_invalidPerson_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readElister("InvalidPerson.csv"));
    }

    @Test
    public void readElister_validElister_success() throws Exception {
        ReadOnlyElister expectedElister = getTypicalElister();
        ReadOnlyElister actualElister = readElister("ValidElister.csv").get();

        assertEquals(expectedElister, actualElister);
    }

    @Test
    public void readAndSaveElister_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempElister.csv");
        Elister original = getTypicalElister();
        CsvElisterStorage csvElisterStorage = new CsvElisterStorage(filePath);

        // Save in new file and read back
        csvElisterStorage.saveElister(original, filePath);
        ReadOnlyElister readBack = csvElisterStorage.readElister(filePath).get();
        assertEquals(original, new Elister(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        csvElisterStorage.saveElister(original, filePath);
        readBack = csvElisterStorage.readElister(filePath).get();
        assertEquals(original, new Elister(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        csvElisterStorage.saveElister(original); // file path not specified
        readBack = csvElisterStorage.readElister().get(); // file path not specified
        assertEquals(original, new Elister(readBack));

    }

    @Test
    public void saveElister_nullElister_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveElister(null, "SomeFile.csv"));
    }

    /**
     * Saves {@code elister} at the specified {@code filePath}.
     */
    private void saveElister(ReadOnlyElister elister, String filePath) {
        try {
            new CsvElisterStorage(Paths.get(filePath))
                    .saveElister(elister, addToTestDataPathIfNotNull(filePath));
        } catch (IOException e) {
            throw new AssertionError("IOException when writing to csv file.", e);
        }
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void saveElister_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveElister(new Elister(), null));
    }
}
