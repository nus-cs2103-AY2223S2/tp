package seedu.sudohr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.HOON;
import static seedu.sudohr.testutil.TypicalEmployees.IDA;
import static seedu.sudohr.testutil.TypicalEmployees.getTypicalSudoHr;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.sudohr.commons.exceptions.DataConversionException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.SudoHr;


public class JsonSudoHrStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSudoHrStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlySudoHr> readAddressBook(String filePath) throws Exception {
        return new JsonSudoHrStorage(Paths.get(filePath)).readSudoHr(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatSudoHr.json"));
    }

    @Test
    public void readAddressBook_invalidEmployeeAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidEmployeeSudoHr.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidEmployeeAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidEmployeeSudoHr.json"));
    }

    @Test
    public void readAddressBook_invalidDepartmentAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidDepartmentSudoHr.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidDepartmentAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readAddressBook("invalidAndValidDepartmentSudoHr.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        SudoHr original = getTypicalSudoHr();
        JsonSudoHrStorage jsonAddressBookStorage = new JsonSudoHrStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveSudoHr(original, filePath);
        ReadOnlySudoHr readBack = jsonAddressBookStorage.readSudoHr(filePath).get();
        assertEquals(original, new SudoHr(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEmployee(HOON);
        original.removeEmployee(ALICE);
        jsonAddressBookStorage.saveSudoHr(original, filePath);
        readBack = jsonAddressBookStorage.readSudoHr(filePath).get();
        assertEquals(original, new SudoHr(readBack));

        // Save and read without specifying file path
        original.addEmployee(IDA);
        jsonAddressBookStorage.saveSudoHr(original); // file path not specified
        readBack = jsonAddressBookStorage.readSudoHr().get(); // file path not specified
        assertEquals(original, new SudoHr(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlySudoHr addressBook, String filePath) {
        try {
            new JsonSudoHrStorage(Paths.get(filePath))
                    .saveSudoHr(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new SudoHr(), null));
    }
}
