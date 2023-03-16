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
    public void readSudoHr_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSudoHr(null));
    }

    private java.util.Optional<ReadOnlySudoHr> readSudoHr(String filePath) throws Exception {
        return new JsonSudoHrStorage(Paths.get(filePath)).readSudoHr(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSudoHr("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSudoHr("notJsonFormatSudoHr.json"));
    }

    @Test
    public void readSudoHr_invalidEmployeeSudoHr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSudoHr("invalidEmployeeSudoHr.json"));
    }

    @Test
    public void readSudoHr_invalidAndValidEmployeeSudoHr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSudoHr("invalidAndValidEmployeeSudoHr.json"));
    }

    @Test
    public void readSudoHr_invalidDepartmentSudoHr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSudoHr("invalidDepartmentSudoHr.json"));
    }

    @Test
    public void readSudoHr_invalidAndValidDepartmentSudoHr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readSudoHr("invalidAndValidDepartmentSudoHr.json"));
    }

    @Test
    public void readAndSaveSudoHr_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSudoHr.json");
        SudoHr original = getTypicalSudoHr();
        JsonSudoHrStorage jsonSudoHrStorage = new JsonSudoHrStorage(filePath);

        // Save in new file and read back
        jsonSudoHrStorage.saveSudoHr(original, filePath);
        ReadOnlySudoHr readBack = jsonSudoHrStorage.readSudoHr(filePath).get();
        assertEquals(original, new SudoHr(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEmployee(HOON);
        original.removeEmployee(ALICE);
        jsonSudoHrStorage.saveSudoHr(original, filePath);
        readBack = jsonSudoHrStorage.readSudoHr(filePath).get();
        assertEquals(original, new SudoHr(readBack));

        // Save and read without specifying file path
        original.addEmployee(IDA);
        jsonSudoHrStorage.saveSudoHr(original); // file path not specified
        readBack = jsonSudoHrStorage.readSudoHr().get(); // file path not specified
        assertEquals(original, new SudoHr(readBack));

    }

    @Test
    public void saveSudoHr_nullSudoHr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSudoHr(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveSudoHr(ReadOnlySudoHr addressBook, String filePath) {
        try {
            new JsonSudoHrStorage(Paths.get(filePath))
                    .saveSudoHr(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSudoHr_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSudoHr(new SudoHr(), null));
    }
}
