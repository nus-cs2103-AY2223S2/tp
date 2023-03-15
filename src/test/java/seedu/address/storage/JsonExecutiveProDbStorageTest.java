package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.HOON;
import static seedu.address.testutil.TypicalEmployees.IDA;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.ReadOnlyExecutiveProDb;

public class JsonExecutiveProDbStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonExecutiveProDbStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExecutiveProDb_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExecutiveProDb(null));
    }

    private java.util.Optional<ReadOnlyExecutiveProDb> readExecutiveProDb(String filePath) throws Exception {
        return new JsonExecutiveProStorage(Paths.get(filePath))
                .readExecutiveProDb(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExecutiveProDb("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExecutiveProDb("notJsonFormatExecutiveProDb.json"));
    }

    @Test
    public void readExecutiveProDb_invalidEmployeeExecutiveProDb_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExecutiveProDb("invalidEmployeeExecutiveProDb.json"));
    }

    @Test
    public void readExecutiveProDb_invalidAndValidEmployeeExecutiveProDb_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readExecutiveProDb("invalidAndValidEmployeeExecutiveProDb.json"));
    }

    @Test
    public void readAndSaveExecutiveProDb_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ExecutiveProDb original = getTypicalExecutiveProDb();
        JsonExecutiveProStorage jsonAddressBookStorage = new JsonExecutiveProStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveExecutiveProDb(original, filePath);
        ReadOnlyExecutiveProDb readBack = jsonAddressBookStorage.readExecutiveProDb(filePath).get();
        assertEquals(original, new ExecutiveProDb(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEmployee(HOON);
        original.removeEmployee(ALICE);
        jsonAddressBookStorage.saveExecutiveProDb(original, filePath);
        readBack = jsonAddressBookStorage.readExecutiveProDb(filePath).get();
        assertEquals(original, new ExecutiveProDb(readBack));

        // Save and read without specifying file path
        original.addEmployee(IDA);
        jsonAddressBookStorage.saveExecutiveProDb(original); // file path not specified
        readBack = jsonAddressBookStorage.readExecutiveProDb().get(); // file path not specified
        assertEquals(original, new ExecutiveProDb(readBack));

    }

    @Test
    public void saveExecutiveProDb_nullExecutiveProDb_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExecutiveProDb(null, "SomeFile.json"));
    }

    /**
     * Saves {@code executiveProDb} at the specified {@code filePath}.
     */
    private void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb, String filePath) {
        try {
            new JsonExecutiveProStorage(Paths.get(filePath))
                    .saveExecutiveProDb(executiveProDb, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExecutiveProDb_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExecutiveProDb(new ExecutiveProDb(), null));
    }
}
