package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Mathutoring;
import seedu.address.model.ReadOnlyMathutoring;


public class JsonMathutoringStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMathutoringStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMathutoring_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMathutoring(null));
    }

    private java.util.Optional<ReadOnlyMathutoring> readMathutoring(String filePath) throws Exception {
        return new JsonMathutoringStorage(Paths.get(filePath)).readMathutoring(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMathutoring("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMathutoring("notJsonFormatMathutoring.json"));
    }

    @Test
    public void readMathutoring_invalidStudentMathutoring_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMathutoring("invalidStudentMathutoring.json"));
    }

    @Test
    public void readMathutoring_invalidAndValidStudentMathutoring_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMathutoring("invalidAndValidStudentMathutoring.json"));
    }

    @Test
    public void readAndSaveMathutoring_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Mathutoring original = getTypicalMathutoring();
        JsonMathutoringStorage jsonMathutoringStorage = new JsonMathutoringStorage(filePath);

        // Save in new file and read back
        jsonMathutoringStorage.saveMathutoring(original, filePath);
        ReadOnlyMathutoring readBack = jsonMathutoringStorage.readMathutoring(filePath).get();
        assertEquals(original, new Mathutoring(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonMathutoringStorage.saveMathutoring(original, filePath);
        readBack = jsonMathutoringStorage.readMathutoring(filePath).get();
        assertEquals(original, new Mathutoring(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonMathutoringStorage.saveMathutoring(original); // file path not specified
        readBack = jsonMathutoringStorage.readMathutoring().get(); // file path not specified
        assertEquals(original, new Mathutoring(readBack));

    }

    @Test
    public void saveMathutoring_nullMathutoring_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMathutoring(null, "SomeFile.json"));
    }

    /**
     * Saves {@code mathutoring} at the specified {@code filePath}.
     */
    private void saveMathutoring(ReadOnlyMathutoring addressBook, String filePath) {
        try {
            new JsonMathutoringStorage(Paths.get(filePath))
                    .saveMathutoring(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMathutoring_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMathutoring(new Mathutoring(), null));
    }
}
