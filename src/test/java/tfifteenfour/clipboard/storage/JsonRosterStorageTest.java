package tfifteenfour.clipboard.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalStudents.ALICE;
import static tfifteenfour.clipboard.testutil.TypicalStudents.HOON;
import static tfifteenfour.clipboard.testutil.TypicalStudents.IDA;
import static tfifteenfour.clipboard.testutil.TypicalStudents.getTypicalRoster;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;

public class JsonRosterStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRoster_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRoster(null));
    }

    private java.util.Optional<ReadOnlyRoster> readRoster(String filePath) throws Exception {
        return new JsonRosterStorage(Paths.get(filePath)).readRoster(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRoster("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRoster("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readRoster_invalidStudentRoster_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoster("invalidStudentAddressBook.json"));
    }

    @Test
    public void readRoster_invalidAndValidStudentRoster_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoster("invalidAndValidStudentAddressBook.json"));
    }

    @Test
    public void readAndSaveRoster_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Roster original = getTypicalRoster();
        JsonRosterStorage jsonRosterStorage = new JsonRosterStorage(filePath);

        // Save in new file and read back
        jsonRosterStorage.saveRoster(original, filePath);
        ReadOnlyRoster readBack = jsonRosterStorage.readRoster(filePath).get();
        assertEquals(original, new Roster(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonRosterStorage.saveRoster(original, filePath);
        readBack = jsonRosterStorage.readRoster(filePath).get();
        assertEquals(original, new Roster(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonRosterStorage.saveRoster(original); // file path not specified
        readBack = jsonRosterStorage.readRoster().get(); // file path not specified
        assertEquals(original, new Roster(readBack));

    }

    @Test
    public void saveRoster_nullRoster_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoster(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveRoster(ReadOnlyRoster addressBook, String filePath) {
        try {
            new JsonRosterStorage(Paths.get(filePath))
                    .saveRoster(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRoster_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoster(new Roster(), null));
    }
}
